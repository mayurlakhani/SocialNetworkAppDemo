package com.meet5.userprofile.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class kafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String userProfileTopic;

    @Value("${spring.kafka.topic-profile-visit.name}")
    private String userProfileVisitTopic;

    @Value("${spring.kafka.topic-profile-list.name}")
    private String userProfileListTopic;
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
}
