package com.messaging.artemis.consumer;

import com.messaging.artemis.ArtemisProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis")
public class ArtemisConsumer {

    private static final Logger LOGGER = Logger.getLogger(ArtemisConsumer.class);

    private JmsTemplate jmsTemplate;

    private ArtemisProperties properties;

    @Autowired
    public ArtemisConsumer(JmsTemplate jmsTemplate, ArtemisProperties artemisProperties) {
        this.jmsTemplate = jmsTemplate;
        this.properties = artemisProperties;
    }

    @JmsListener(destination = "artemis.test1")
    public void receive(String msg) {
        LOGGER.debug("Received: <" + msg + ">");
        jmsTemplate.convertAndSend("artemis.test2", msg);
    }


}
