package com.meet5.kafkaconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet5.userprofile.dto.IdDto;

import com.meet5.kafkaconsumer.model.ProfileVisits;
import com.meet5.kafkaconsumer.model.UserProfile;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class kafkaConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserProfileService userProfileService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "userProfileGroup")
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
    }

    @KafkaListener(topics = "${spring.kafka.topic-profile-visit.name}", groupId = "userProfileGroup")
    public String createUserProfileVisits(String idDto) {
        try {
            IdDto ids = objectMapper.readValue(idDto, IdDto.class);
            log.info("message received: " + ids.toString());
            if(ids.getActivity().equals("visit")){
                int profileVisitsRes = userProfileService.createUserProfileVisitor(ids.getUser_id(), ids.getVisitor_id());
                if (profileVisitsRes > 0) {
                    log.info("user's profile visit saved");
                }
            }else if(ids.getActivity().equals("like")) {
                int profileLikes = userProfileService.createUserProfileLikes(ids.getUser_id(), ids.getVisitor_id());
                if (profileLikes > 0) {
                    log.info("user's profile like saved");

                }
            }
            else{
                List<ProfileVisits> profileVisitsList= userProfileService.findProfileVisitorsByUserId(ids.getUser_id());
                if(profileVisitsList.isEmpty()){
                    return "you have no visitors yet";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "something went wrong";
    }
    @KafkaListener(
            topics = "${spring.kafka.topic-profile-list.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void BulkDataInsertion(String eventMessage){
        userProfileService.bulkDataInsertion(eventMessage);
    }
}
