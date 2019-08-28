package com.messaging.artemis.config;

import com.messaging.artemis.producer.ArtemisProducer;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Profile("artemis")
public class ArtemisProducerConfig {

    @Value("${artemis.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(
                senderActiveMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(cachingConnectionFactory());
    }

    @Bean
    public ArtemisProducer sender() {
        ArtemisProducer artemisProducer = new ArtemisProducer();
        artemisProducer.send("Hello World!");
        return artemisProducer;
    }
}
