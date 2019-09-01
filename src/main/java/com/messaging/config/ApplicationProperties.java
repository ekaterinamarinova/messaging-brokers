package com.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties
public class ApplicationProperties {

    private int rabbitClientDuration;

    private String artemisBrokerUrl;

    private String jmsDestinationQueue;

    private String kafkaBootstrapAddress;

    public int getRabbitClientDuration() {
        return rabbitClientDuration;
    }

    public void setRabbitClientDuration(int rabbitClientDuration) {
        this.rabbitClientDuration = rabbitClientDuration;
    }

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

    public String getKafkaBootstrapAddress() {
        return kafkaBootstrapAddress;
    }

    public void setKafkaBootstrapAddress(String kafkaBootstrapAddress) {
        this.kafkaBootstrapAddress = kafkaBootstrapAddress;
    }
}
