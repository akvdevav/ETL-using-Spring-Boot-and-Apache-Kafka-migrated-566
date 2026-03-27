package com.example.demo.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@EnableRabbit
@Configuration
public class RabbitConsumerConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // Additional factory settings can be configured here if needed
        return factory;
    }

    // Overloaded method without parameters for legacy callers
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        return rabbitListenerContainerFactory(this.connectionFactory);
    }
}