package com.messaging.artemis.consumer;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis")
public class ArtemisConsumer {

    private static final Logger LOGGER = Logger.getLogger(ArtemisConsumer.class);

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) {
        LOGGER.debug("Received: <" + msg + ">");
    }
}
