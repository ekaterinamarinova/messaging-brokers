package com.messaging.rabbit.config;

import com.messaging.rabbit.consumer.RabbitConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitConfig {

    private static final String QUEUE_NAME = "my.queue";

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public RabbitConsumer receiver(RabbitTemplate rabbitTemplate) {
        return new RabbitConsumer(rabbitTemplate);
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RabbitConsumer receiver) {
        return new MessageListenerAdapter(receiver, "receive");
    }

    @Bean
    public Queue hello() {
        return new Queue(QUEUE_NAME);
    }

}
