package com.messaging.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Profile("rabbit")
public class RabbitProducer {

    private static final Logger LOGGER = Logger.getGlobal();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello World!";
        rabbitTemplate.convertAndSend(queue.getName(), message);
        LOGGER.log(Level.FINE, "[x] Sent :" + message);
    }

}
