package com.example.demo.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for RabbitMQ consumers.
 * Provides a {@link SimpleRabbitListenerContainerFactory} bean used by {@code @RabbitListener}
 * endpoints and exposes utility getters for legacy callers.
 */
@EnableRabbit
@Configuration
public class RabbitConsumerConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * Primary container factory bean used by {@code @RabbitListener}.
     *
     * @return a configured {@link SimpleRabbitListenerContainerFactory}
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    /**
     * Legacy overload that allows callers to supply their own {@link ConnectionFactory}.
     *
     * @param cf the connection factory to use
     * @return a configured {@link SimpleRabbitListenerContainerFactory}
     */
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory cf) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        return factory;
    }

    /**
     * Getter for legacy code that expects to retrieve the container factory directly.
     *
     * @return the primary {@link SimpleRabbitListenerContainerFactory}
     */
    public SimpleRabbitListenerContainerFactory getRabbitListenerContainerFactory() {
        return rabbitListenerContainerFactory();
    }

    /**
     * Getter for legacy code that needs direct access to the {@link ConnectionFactory}.
     *
     * @return the injected {@link ConnectionFactory}
     */
    public ConnectionFactory getConnectionFactory() {
        return this.connectionFactory;
    }
}