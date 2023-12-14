package com.example.demo.rabbitmq.receive;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 由消息监听器推送消息
 */
@Component
@Slf4j
public class AnnotationDriven {

    @SneakyThrows
    @RabbitListener(queues = "helloWorldQueue")
    public void receiveMessageByAnnotation(String msg, Message message, Channel channel){

        log.info("msg detected -> [{}]", msg);
//        log.info("Ready message count : [{}]", channel.messageCount("helloWorldQueue"));
//        log.info("Consumer count: [{}]", channel.consumerCount("helloWorldQueue"));
//        log.info("Message info -> {}", message);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
