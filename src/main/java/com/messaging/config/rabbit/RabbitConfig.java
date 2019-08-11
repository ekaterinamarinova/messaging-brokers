package com.messaging.config.rabbit;

import com.messaging.consumer.RabbitConsumer;
import com.messaging.producer.RabbitProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig implements CommandLineRunner {

    private static final String QUEUE_NAME = "hello";

    @Value("${rabbit.client.duration:0}")
    private int duration;

    @Autowired
    private ConfigurableApplicationContext ctx;

    @Bean
    public Queue hello() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public RabbitConsumer receiver() {
        return new RabbitConsumer();
    }

    @Bean
    public RabbitProducer sender() {
        return new RabbitProducer();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ready ... running for " + duration);
        Thread.sleep(duration);
        ctx.close();
    }
}
