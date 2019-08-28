package com.messaging.kafka.producer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Profile("kafka")
public class KafkaProducer {

    private static final Logger LOGGER = Logger.getLogger(KafkaProducer.class);
    private static final String MESSAGE = "Hello World";
    private static final String TOPIC = "kafka";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC, MESSAGE);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message!");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message: " + MESSAGE);
            }
        });

        LOGGER.debug("Sent message: " + MESSAGE);
    }
}
