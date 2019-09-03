package com.messaging.rabbit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperties {

    private int rabbitClientDuration;

    public int getRabbitClientDuration() {
        return rabbitClientDuration;
    }

    public void setRabbitClientDuration(int rabbitClientDuration) {
        this.rabbitClientDuration = rabbitClientDuration;
    }
}
