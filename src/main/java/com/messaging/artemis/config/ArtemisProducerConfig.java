package com.messaging.artemis.config;

import com.messaging.artemis.ArtemisProperties;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Profile("artemis")
public class ArtemisProducerConfig {

    private ArtemisProperties properties;

    @Autowired
    public ArtemisProducerConfig(ArtemisProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(senderActiveMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(cachingConnectionFactory());
    }

}
