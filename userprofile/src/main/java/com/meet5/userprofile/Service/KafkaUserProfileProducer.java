package com.meet5.userprofile.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet5.userprofile.dto.IdDto;
import com.meet5.userprofile.dto.UserProfileEvent;
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
public class KafkaUserProfileProducer {
    @Value("${spring.kafka.consumer.topic1}")
    private String userProfileTopic;

   @Value("${spring.kafka.consumer.topic2}")
    private String userProfileVisitTopic;

   @Value("${spring.kafka.consumer.topic3}")
    private String userProfileListTopic;
    @Autowired
    private KafkaTemplate<String, UserProfile> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, UserProfileEvent> userProfileKafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendUserProfileEventMessage(UserProfileEvent event){
        log.info("UserProfile created event => "+ event.toString());
        Message<UserProfileEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, userProfileTopic)
                .build();

        userProfileKafkaTemplate.send(message);

    }
  /*  public void sendUserProfileRequest(UserProfile user) {
        log.info(String.format("userProfile event => %s", user.toString()));

        Message<UserProfile> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, userProfileTopic)
                .build();

        kafkaTemplate.send(message);
    }*/

    public void sendProfileVisitorOrProfileLikerId(IdDto idDto)  {


        Message<IdDto> message = MessageBuilder
                .withPayload(idDto)
                .setHeader(KafkaHeaders.TOPIC, userProfileVisitTopic)
                .build();

        kafkaTemplate.send(message);
    }

    public void insertBulkData(List<UserProfile> dataList) {
        Message<List<UserProfile>> message = MessageBuilder
                .withPayload(dataList)
                .setHeader(KafkaHeaders.TOPIC, userProfileListTopic)
                .build();

        kafkaTemplate.send(message);
    }
}
