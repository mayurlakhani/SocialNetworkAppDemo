package com.meet5.userprofile.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet5.userprofile.dto.IdDto;
import com.meet5.userprofile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileProducer {
    @Value("${spring.kafka.topic.name}")
    private String userProfileTopic;

    @Value("${spring.kafka.topic-profile-visit.name}")
    private String userProfileVisitTopic;

    @Value("${spring.kafka.topic-profile-list}")
    private String userProfileListTopic;
    @Autowired
    private KafkaTemplate<String, UserProfile> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    public void sendUserProfileRequest(UserProfile user) {
        log.info(String.format("userProfile event => %s", user.toString()));

        Message<UserProfile> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, userProfileTopic)
                .build();

        kafkaTemplate.send(message);
    }

    public void sendProfileVisitorOrProfileLikerId(IdDto idDto) {
        log.info(String.format("IdList transfer event => %s", idDto.toString()));
        Message<IdDto> message = MessageBuilder
                .withPayload(idDto)
                .setHeader(KafkaHeaders.TOPIC, userProfileListTopic)
                .build();

        kafkaTemplate.send(message);
    }

    public void insertBulkData(List<UserProfile> dataList) {
        Message<List<UserProfile>> message = MessageBuilder
                .withPayload(dataList)
                .setHeader(KafkaHeaders.TOPIC, userProfileVisitTopic)
                .build();

        kafkaTemplate.send(message);
    }
}
