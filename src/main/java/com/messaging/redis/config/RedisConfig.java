package com.messaging.redis.config;

import com.messaging.redis.RedisProperties;
import com.messaging.redis.consumer.RedisConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@Profile("redis")
public class RedisConfig {

    private RedisProperties redisProperties;

    @Autowired
    public RedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            org.springframework.data.redis.listener.adapter.MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(redisProperties.getChannelName()));

        return container;
    }

    @Bean
    org.springframework.data.redis.listener.adapter.MessageListenerAdapter listenerAdapter(RedisConsumer receiver) {
        return new org.springframework.data.redis.listener.adapter.MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
