package com.meet5.kafkaconsumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerTopic {

    @Value("${spring.kafka.consumer.topicRes}")
    private String notify_res;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(notify_res)
                .build();
    }

}
