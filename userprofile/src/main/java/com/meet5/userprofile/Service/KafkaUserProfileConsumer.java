package com.meet5.userprofile.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.meet5.userprofile.dto.NotificationExtractor;
import com.meet5.userprofile.dto.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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

}
