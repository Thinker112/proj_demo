package com.example.demo.rabbitmq.conf;


import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private final AmqpAdmin amqpAdmin;

    public RabbitMqConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void setupQueues() {
        amqpAdmin.declareQueue(queue());
        amqpAdmin.declareQueue(queue2());
    }

    @Bean
    public Queue queue() {
        return new Queue("helloWorldQueue", false, false, false);
    }
    @Bean
    public Queue queue2() {
        return new Queue("helloWorld2Queue", false, false, false);
    }

    // 定义直连交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("test_monitor_platform", true, true);
    }

    // 绑定队列到直连交换机
    @Bean
    public Binding binding1(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("alarm_linkage_routingKey");
    }
    @Bean
    public Binding binding2(Queue queue2, DirectExchange directExchange) {
        return BindingBuilder.bind(queue2).to(directExchange).with("alarm_linkage_routingKey");
    }
}
