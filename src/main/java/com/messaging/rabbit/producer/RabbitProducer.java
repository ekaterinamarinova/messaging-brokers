package com.messaging.rabbit.producer;

import com.messaging.rabbit.consumer.RabbitConsumer;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
@Profile("rabbit")
public class RabbitProducer {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(RabbitProducer.class);

    private RabbitTemplate rabbitTemplate;
    private Queue queue;

    @Autowired
    public RabbitProducer(Queue queue, RabbitTemplate rabbitTemplate) {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String message = "Hello World!";
        rabbitTemplate.convertAndSend(queue.getName(), message);
        LOGGER.debug("[X] Sent message: <" + message + ">");
    }

}
