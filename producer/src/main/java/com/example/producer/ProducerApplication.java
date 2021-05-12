package com.example.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 100; i++) {
            String msg = "第" + i + "条消息";
            System.out.println("==> 发送" + msg);
            if (i % 2 == 1) {
                rabbitTemplate.convertAndSend(Constants.HORSE_SIMPLE_EXCHANGE, Constants.HORSE_SIMPLE_KEY, msg, new CorrelationData(String.valueOf(i)));
            } else {
                rabbitTemplate.convertAndSend(Constants.HORSE_ANNOTATION_EXCHANGE, Constants.HORSE_ANNOTATION_KEY, msg, new CorrelationData(String.valueOf(i)));
            }
        }
    }
}
