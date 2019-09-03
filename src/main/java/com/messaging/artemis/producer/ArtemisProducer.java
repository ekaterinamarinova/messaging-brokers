package com.messaging.artemis.producer;

import com.messaging.artemis.ArtemisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis")
public class ArtemisProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ArtemisProperties properties;

    public void send(String msg){
        jmsTemplate.convertAndSend(properties.getJmsDestinationQueue(), msg);
    }
}
