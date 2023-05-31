package com.example.amqp_mq_demo.receive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 主动从队列中取消息（轮询方式）
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class PollingReceive {

    final AmqpTemplate amqpTemplate;

    @GetMapping("/getMessage")
    public String getMessage(){
        int i = 0;
        while (true){//轮询取出队列的所有消息
            //doReceiveNoWait方法中已调用basicAck()
            String helloWorldQueue = (String) amqpTemplate.receiveAndConvert("helloWorldQueue");
            if (helloWorldQueue == null) break;
            log.info("接收到消息：{}", helloWorldQueue);
            i++;
        }

        return Integer.toString(i);
    }

}
