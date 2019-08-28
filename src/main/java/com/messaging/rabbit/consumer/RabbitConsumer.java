package com.messaging.rabbit.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
@Profile("rabbit")
public class RabbitConsumer {

    private static final Logger LOGGER = Logger.getLogger(RabbitConsumer.class);

    @RabbitHandler
    public void receive(String message) {

        LOGGER.debug("Received: <" + message + ">");
    }
}
