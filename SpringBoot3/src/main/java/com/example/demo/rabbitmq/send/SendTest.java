package com.example.demo.rabbitmq.send;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendTest {

    final RabbitTemplate rabbitTemplate;

    int i = 0;

/*    @Scheduled(initialDelay = 3 * 1000, fixedDelay = 8 * 1000)
    public void send(){
        i++;
        String messages = "hello: " + i;
        CorrelationData correlationData = new CorrelationData();

        rabbitTemplate.send("helloWorldQueue", new Message(messages.getBytes()), correlationData);

        rabbitTemplate.setConfirmCallback((correlationInfo, ack, cause) -> {
            if (!ack){
                log.error("消息投递到交换机失败， reason: {}， returned msg: {}", cause, correlationInfo.getReturned());
            }
            log.info("消息投递到交换机成功！！！");
        });

        //RabbitTemplate中只能存在一个ReturnsCallback
        rabbitTemplate.setReturnsCallback(returned -> log.error("消息投递到队列失败，returned msg: {}", returned));

        log.info("send message -> {}", messages);
    }*/

//    @Scheduled(initialDelay = 3 * 1000, fixedDelay = 3 * 1000)
    public void send(){
        i++;
        String messages = "hello: " + i;
//        CorrelationData correlationData = new CorrelationData();

        rabbitTemplate.send("test_monitor_platform", "alarm_linkage_routingKey", new Message(messages.getBytes()));

//        try {
//            CorrelationData.Confirm confirm = correlationData.getFuture().get();
//            if (!confirm.isAck()){
//                log.error("error");
//            }
//            log.info("success!");
//
//
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        //RabbitTemplate中只能存在一个ReturnsCallback
        rabbitTemplate.setReturnsCallback(returned -> log.error("消息投递到队列失败，returned msg: {}", returned));

        log.info("send message -> {}", messages);
    }

}
