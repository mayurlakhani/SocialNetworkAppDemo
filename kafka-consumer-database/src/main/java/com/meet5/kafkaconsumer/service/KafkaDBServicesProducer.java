package com.meet5.kafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet5.kafkaconsumer.model.ProfileVisits;
import com.meet5.userprofile.dto.ResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaDBServicesProducer {

    @Value("${spring.kafka.consumer.topicRes}")
    private String res_notification;

    @Value("${spring.kafka.consumer.topic4}")
    private String profileVisitorsList;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendMessage(ResponseEvent msg) throws JsonProcessingException {
        String responseEvent = objectMapper.writeValueAsString(msg);
        Message<String> message = MessageBuilder
                .withPayload(responseEvent)
                .setHeader(KafkaHeaders.TOPIC, res_notification)
                .build();

        kafkaTemplate.send(message);
    }

    public void sendProfileVisitorsList(List<ProfileVisits> msg) throws JsonProcessingException {
        String responseEvent = objectMapper.writeValueAsString(msg);
        Message<String> message = MessageBuilder
                .withPayload(responseEvent)
                .setHeader(KafkaHeaders.TOPIC, profileVisitorsList)
                .build();

        kafkaTemplate.send(message);
    }
}
