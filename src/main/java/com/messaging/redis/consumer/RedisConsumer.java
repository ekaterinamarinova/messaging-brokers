package com.messaging.redis.consumer;

import com.messaging.redis.RedisProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConsumer {

    private static final Logger LOGGER = Logger.getLogger(RedisConsumer.class);

    private StringRedisTemplate redisTemplate;
    private RedisProperties redisProperties;

    @Autowired
    public RedisConsumer(StringRedisTemplate redisTemplate, RedisProperties redisProperties) {
        this.redisTemplate = redisTemplate;
        this.redisProperties = redisProperties;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received: <" + message + ">");
        redisTemplate.convertAndSend(redisProperties.getChannelName2(), message);
    }
}
