package com.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhujh
 * @date 2021/5/7
 */
@Component
@Slf4j
public class MsgListener {


    /**
     * 配置类中已经完成绑定,这里直接根据队列值接收
     *
     * @param message
     * @param channel
     * @param msg
     */
    @RabbitListener(queues = Constants.HORSE_SIMPLE_QUEUE)
    public void customListener(Message message, Channel channel, String msg) {
        // 获取每条消息唯一标识(用于手动ACK确认)
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info(" ==> customListener接收" + msg);
            // 手动ACK确认
            channel.basicAck(tag, false);
        } catch (IOException e) {
            log.error(" ==> 消息接收失败: {}", tag);
        }
    }

    /**
     * 根据注解的形式进行绑定接收
     *
     * @param message
     * @param channel
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constants.HORSE_ANNOTATION_QUEUE),
            exchange = @Exchange(value = Constants.HORSE_ANNOTATION_EXCHANGE, ignoreDeclarationExceptions = "true"),
            key = {Constants.HORSE_ANNOTATION_KEY}
    ))
    public void annotationListener(Message message, Channel channel, String msg) {
        // 获取每条消息唯一标识(用于手动ACK确认)
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info(" ==> annotationListener接收" + msg);
            // 手动ACK确认
            channel.basicAck(tag, false);
        } catch (IOException e) {
            log.error(" ==> 消息接收失败: {}", tag);
        }
    }

}
