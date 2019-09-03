package com.messaging.artemis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "artemis")
public class ArtemisProperties {
    private String artemisBrokerUrl;

    private String jmsDestinationQueue;

    public String getArtemisBrokerUrl() {
        return artemisBrokerUrl;
    }

    public void setArtemisBrokerUrl(String artemisBrokerUrl) {
        this.artemisBrokerUrl = artemisBrokerUrl;
    }

    public String getJmsDestinationQueue() {
        return jmsDestinationQueue;
    }

    public void setJmsDestinationQueue(String jmsDestinationQueue) {
        this.jmsDestinationQueue = jmsDestinationQueue;
    }
}
