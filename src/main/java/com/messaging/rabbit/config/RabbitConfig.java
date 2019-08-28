package com.messaging.rabbit.config;

import com.messaging.rabbit.consumer.RabbitConsumer;
import com.messaging.rabbit.producer.RabbitProducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitConfig {

    private static final String QUEUE_NAME = "hello";

    private static final String TOPIC_EXCHANGE_NAME = "hello-exchange";

    @Value("${rabbit.client.duration:0}")
    private int duration;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("foo.bar.#");
    }

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
    public RabbitConsumer receiver() {
        return new RabbitConsumer();
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RabbitConsumer receiver) {
        return new MessageListenerAdapter(receiver, "receive");
    }

    @Bean
    public Queue hello() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public RabbitProducer sender(Queue queue, RabbitTemplate rabbitTemplate) {
        RabbitProducer rabbitProducer = new RabbitProducer(queue, rabbitTemplate);
        rabbitProducer.send();
        return rabbitProducer;
    }
}
