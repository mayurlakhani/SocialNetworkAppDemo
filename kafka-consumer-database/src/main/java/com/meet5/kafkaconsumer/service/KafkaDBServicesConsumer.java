package com.meet5.kafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.meet5.userprofile.dto.ResponseEvent;
import com.meet5.userprofile.dto.IdDto;
import com.meet5.kafkaconsumer.model.UserProfile;
import com.meet5.kafkaconsumer.model.ProfileVisits;
import com.meet5.userprofile.dto.UserProfileEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class KafkaDBServicesConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    KafkaDBServicesProducer kafkaDBServicesProducer;

   /* @KafkaListener(topics = "${spring.kafka.consumer.topic1}", groupId = "${spring.kafka.consumer.group-id}")
    public String createUserProfile(String userProfile) {
        try {
            UserProfile up = objectMapper.readValue(userProfile, UserProfile.class);
            log.info("message received: " + up.toString());
            int userprofileRes = userProfileService.createUserprofile(up);
            if (userprofileRes > 0) {
                return "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error occur";
    }*/

    @KafkaListener(topics = "${spring.kafka.consumer.topic1}", groupId = "${spring.kafka.consumer.group-id}")
    public void createUserProfileEvent(String userProfileEvent) throws JsonProcessingException {
        ResponseEvent responseEvent = new ResponseEvent();
        try {
            UserProfileEvent up = objectMapper.readValue(userProfileEvent, UserProfileEvent.class);
            log.info("event received for userProfileEvent: " + up.toString());
            UserProfile userProfile = new UserProfile();
            userProfile.setAge(up.getUserProfile().getAge());
            userProfile.setUserId(up.getUserProfile().getUserId());
            userProfile.setName(up.getUserProfile().getName());
            userProfile.setEmail(up.getUserProfile().getEmail());
            userProfile.setFraudulent(false);
            int userprofileRes = userProfileService.createUserprofile(userProfile); // to the service
            if (userprofileRes > 0) {
                responseEvent.setEvent("UserProfileCreation");
                responseEvent.setResponse_for("CreateUserProfile");
                responseEvent.setStatus("CREATED");
                kafkaDBServicesProducer.sendMessage(responseEvent);     // send the status to the UserProfile microservice
            }
        } catch (Exception e) {
            responseEvent.setEvent("UserProfileCreation");
            responseEvent.setResponse_for("ErrorUserProfile");
            responseEvent.setStatus("NOT_FOUND");
            kafkaDBServicesProducer.sendMessage(responseEvent);
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "${spring.kafka.consumer.topic2}", groupId = "${spring.kafka.consumer.group-id}")
    public void createUserProfileVisits(String idDto) {
        ResponseEvent responseEvent = new ResponseEvent();
        try {
            IdDto ids = objectMapper.readValue(idDto, IdDto.class);
            log.info("message received: " + ids.toString());
            if (ids.getActivity().equals("visit")) {
                int profileVisitsRes = userProfileService.createUserProfileVisitor(ids.getUser(), ids.getVisitor());
                if (profileVisitsRes > 0) {
                    log.info("user's profile visit saved");
                    responseEvent.setEvent("CreateProfileVisit");
                    responseEvent.setResponse_for("ProfileVisit");
                    responseEvent.setStatus("CREATED");
                    kafkaDBServicesProducer.sendMessage(responseEvent);

                }else {
                    responseEvent.setEvent("CreateProfileVisitError");
                    responseEvent.setResponse_for("ProfileVisit");
                    responseEvent.setStatus("NOT_FOUND");
                    kafkaDBServicesProducer.sendMessage(responseEvent);
                }
            } else if (ids.getActivity().equals("like")) {
                int profileLikes = userProfileService.createUserProfileLikes(ids.getUser(), ids.getVisitor());
                if (profileLikes > 0) {
                    log.info("user's profile like saved");
                    responseEvent.setEvent("CreateProfileLike");
                    responseEvent.setResponse_for("ProfileLike");
                    responseEvent.setStatus("CREATED");
                    kafkaDBServicesProducer.sendMessage(responseEvent);
                }
                else {
                    responseEvent.setEvent("CreateProfileLikeError");
                    responseEvent.setResponse_for("ProfileLike");
                    responseEvent.setStatus("NOT_FOUND");
                    kafkaDBServicesProducer.sendMessage(responseEvent);
                }
            } else if (ids.getActivity().equals("fetch")) {
                List<ProfileVisits> profileVisitsList = userProfileService.findProfileVisitorsByUserId(ids.getUser());
                if (profileVisitsList.isEmpty()) {
                    responseEvent.setEvent("FetchVisitorListError");
                    responseEvent.setResponse_for("Visitor");
                    responseEvent.setStatus("NOT_FOUND");
                    kafkaDBServicesProducer.sendMessage(responseEvent);
                }else {
                    responseEvent.setEvent("FetchVisitorListSuccess");
                    responseEvent.setResponse_for("Visitor");
                    responseEvent.setStatus("OK");
                    kafkaDBServicesProducer.sendMessage(responseEvent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @KafkaListener(
            topics = "${spring.kafka.consumer.topic3}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void BulkDataInsertion(String eventMessage) throws JsonProcessingException {
        int result = userProfileService.bulkDataInsertion(eventMessage);
        ResponseEvent responseEvent = new ResponseEvent();
        if (result > 0) {
            responseEvent.setEvent("InsertBulkData");
            responseEvent.setResponse_for("UserProfileList");
            responseEvent.setStatus("OK");
            kafkaDBServicesProducer.sendMessage(responseEvent);
        }else{
            responseEvent.setEvent("InsertBulkDataError");
            responseEvent.setResponse_for("UserProfileList");
            responseEvent.setStatus("NO_CONTENT");
            kafkaDBServicesProducer.sendMessage(responseEvent);
        }
    }
}
