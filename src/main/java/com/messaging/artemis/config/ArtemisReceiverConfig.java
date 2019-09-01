package com.messaging.artemis.config;

import com.messaging.artemis.consumer.ArtemisConsumer;
import com.messaging.config.ApplicationProperties;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
@Profile("artemis")
public class ArtemisReceiverConfig {

    @Autowired
    private ApplicationProperties properties;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory(properties.getArtemisBrokerUrl());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ApplicationProperties properties) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory
                .setConnectionFactory(receiverActiveMQConnectionFactory());
        factory.setConcurrency("3-10");

        return factory;
    }

    @Bean
    public ArtemisConsumer receiver() {
        return new ArtemisConsumer();
    }
}