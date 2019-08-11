package com.messaging.producer;

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
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String MESSAGE = "messsage";
    private static final String TOPIC = "kafka1";

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
    }
}
