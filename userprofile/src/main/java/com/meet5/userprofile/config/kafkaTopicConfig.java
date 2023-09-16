package com.meet5.userprofile.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class kafkaTopicConfig {

<<<<<<< HEAD
    @Value("${spring.kafka.consumer.topic1}")
    private String userProfileTopic;

    @Value("${spring.kafka.consumer.topic2}")
    private String userProfileVisitTopic;

    @Value("${spring.kafka.consumer.topic3}")
    private String userProfileListTopic;

    @Value("${spring.kafka.consumer.topic4}")
    private String profileVisitsList;
=======
    @Value("${spring.kafka.topic.name}")
    private String userProfileTopic;

    @Value("${spring.kafka.topic-profile-visit.name}")
    private String userProfileVisitTopic;

    @Value("${spring.kafka.topic-profile-list.name}")
    private String userProfileListTopic;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
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
<<<<<<< HEAD
    @Bean
    public NewTopic topicProfileVisitList() {
        return TopicBuilder.name(profileVisitsList)
                .build();
    }
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
}
