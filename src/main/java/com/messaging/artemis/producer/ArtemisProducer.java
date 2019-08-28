package com.messaging.artemis.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis")
public class ArtemisProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    private String destinationQueue;

    public void send(String msg){
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
