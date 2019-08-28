package com.messaging.artemis.config;

import com.messaging.artemis.consumer.ArtemisConsumer;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
@Profile("artemis")
public class ArtemisReceiverConfig {

    @Value("${artemis.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
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