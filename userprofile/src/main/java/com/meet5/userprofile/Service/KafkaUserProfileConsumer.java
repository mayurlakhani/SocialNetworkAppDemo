package com.meet5.userprofile.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.meet5.userprofile.dto.NotificationExtractor;
import com.meet5.userprofile.dto.ResponseEvent;
import com.meet5.userprofile.dto.ResponseProfileVisitorList;
import com.meet5.userprofile.model.ProfileVisits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaUserProfileConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @KafkaListener(topics = "${spring.kafka.consumer.topicRes}", groupId = "userProfile")
    public void receiveMessage(String message) throws JsonProcessingException {
        ResponseEvent responseEvent = objectMapper.readValue(message, ResponseEvent.class);
        log.info("Received message event:" + message);
    }
    @KafkaListener(topics = "${spring.kafka.consumer.topic4}", groupId = "userProfile")
    public void receiveProfileVisitsList(String message) throws JsonProcessingException {
       //String messageList = objectMapper.writeValueAsString(message);
        List<ProfileVisits> userProfileVisitorList = objectMapper.readValue(message, new TypeReference<List<ProfileVisits>>() {
        });
        //log.info("Received message event:" + userProfileVisitorList);

        ResponseProfileVisitorList.setProfileVisitsList(userProfileVisitorList);
    }


}
