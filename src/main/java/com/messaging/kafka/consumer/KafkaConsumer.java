package com.messaging.kafka.consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class KafkaConsumer {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumer.class);

//    private KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    public KafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    @KafkaListener(topics = "kafka", groupId = "group_id")
    public void receive(String message) {
        LOGGER.debug("Received: <" + message + ">");
//        kafkaTemplate.send("kafka", message);

    }
}
