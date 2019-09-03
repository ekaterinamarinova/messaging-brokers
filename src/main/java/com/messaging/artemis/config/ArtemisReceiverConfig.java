package com.messaging.artemis.config;

import com.messaging.artemis.ArtemisProperties;
import com.messaging.artemis.consumer.ArtemisConsumer;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
@Profile("artemis")
public class ArtemisReceiverConfig {

    private ArtemisProperties properties;

    @Autowired
    public ArtemisReceiverConfig(ArtemisProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ArtemisProperties properties) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(receiverActiveMQConnectionFactory());
        factory.setConcurrency("3-10");

        return factory;
    }

    @Bean
    public ArtemisConsumer receiver(JmsTemplate jmsTemplate, ArtemisProperties artemisProperties) {
        return new ArtemisConsumer(jmsTemplate, artemisProperties);
    }
}