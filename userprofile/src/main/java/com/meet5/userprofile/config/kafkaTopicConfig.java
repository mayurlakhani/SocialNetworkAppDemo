package com.meet5.userprofile.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class kafkaTopicConfig {

    @Value("${spring.kafka.consumer.topic1}")
    private String userProfileTopic;

    @Value("${spring.kafka.consumer.topic2}")
    private String userProfileVisitTopic;

    @Value("${spring.kafka.consumer.topic3}")
    private String userProfileListTopic;

    @Value("${spring.kafka.consumer.topic4}")
    private String profileVisitsList;
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(userProfileTopic)
                .build();
    }

    @Bean
    public NewTopic topicProfileList() {
        return TopicBuilder.name(userProfileListTopic)
                .build();
    }

    @Bean
    public NewTopic topicProfileVisit() {
        return TopicBuilder.name(userProfileVisitTopic)
                .build();
    }
    @Bean
    public NewTopic topicProfileVisitList() {
        return TopicBuilder.name(profileVisitsList)
                .build();
    }
}
