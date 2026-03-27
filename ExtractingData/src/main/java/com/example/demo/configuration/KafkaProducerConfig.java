package com.example.demo.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Bean
    public Queue demoQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // Create a RabbitTemplate with the provided connection factory.
        // No explicit queue binding is required here; the template can be used
        // with convertAndSend specifying the target queue name at call time.
        return new RabbitTemplate(connectionFactory);
    }
}