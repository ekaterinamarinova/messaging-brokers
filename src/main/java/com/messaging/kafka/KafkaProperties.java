package com.messaging.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String brokerUrl;

    public String getBrokerUrl() {
        return brokerUrl;
    }
    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

}