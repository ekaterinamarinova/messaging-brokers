package com.messaging.rabbit.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "my.queue")
@Profile("rabbit")
public class RabbitConsumer {

    private static final Logger LOGGER = Logger.getLogger(RabbitConsumer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitHandler
    public void receive(byte[] message) {
        String msg = new String(message);
        LOGGER.debug("Received: <" + msg + ">");
        rabbitTemplate.convertAndSend("my.exchange", message);
    }
}
