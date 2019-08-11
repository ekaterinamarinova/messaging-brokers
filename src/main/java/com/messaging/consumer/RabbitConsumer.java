package com.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@RabbitListener(queues = "hello")
public class RabbitConsumer {

    private static final Logger LOGGER = Logger.getGlobal();

    @RabbitHandler
    public void receive(String in) {
        LOGGER.log(Level.FINE, "Received message: " + in);
    }
}
