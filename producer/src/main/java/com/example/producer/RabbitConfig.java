package com.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhujh
 * @date 2021/5/7
 */
@Configuration
@Slf4j
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;

    /**
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 触发setReturnCallback回调必须设置mandatory=true,否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(Boolean.TRUE);
        // 设置序列化机制
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 消息由投递到Exchange中时触发的回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送到Exchange情况反馈:唯一标识：correlationData={},消息确认：ack={},原因：cause={}",
                        correlationData, ack, cause)
        );
        // 消息由Exchange发送到Queue时失败触发的回调
        rabbitTemplate.setReturnsCallback((returnedMessage) -> {
            // 如果是插件形式实现的延时队列,则直接返回
            // 原因: 因为发送方确实没有投递到队列上，只是在交换器上暂存，等过期时间到了 才会发往队列,从而实现延时队列的操作
            if (Constants.HORSE_PLUGIN_EXCHANGE.equals(returnedMessage.getExchange())) {
                return;
            }
            log.warn("消息由Exchange发送到Queue时失败:message={},replyCode={},replyText={},exchange={},rountingKey={}",
                    returnedMessage.getMessage(), returnedMessage.getReplyText(), returnedMessage.getReplyText(),
                    returnedMessage.getExchange(), returnedMessage.getRoutingKey());
        });
        return rabbitTemplate;
    }

    //*******************************************直接配置绑定关系*****************************************
    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue horseQueue() {
        return new Queue(Constants.HORSE_SIMPLE_QUEUE, Boolean.TRUE);
    }

    /**
     * 声明指定模式交换机
     *
     * @return
     */
    @Bean
    public DirectExchange horseExchange() {
        return new DirectExchange(Constants.HORSE_SIMPLE_EXCHANGE, Boolean.TRUE, Boolean.FALSE);
    }

    /**
     * 绑定交换机,队列,路由Key
     *
     * @return
     */
    @Bean
    public Binding horseBinding() {
        return BindingBuilder.bind(horseQueue()).to(horseExchange()).with(Constants.HORSE_SIMPLE_KEY);
    }

}
