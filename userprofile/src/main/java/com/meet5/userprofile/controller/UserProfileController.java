package com.meet5.userprofile.controller;
<<<<<<< HEAD
<<<<<<< HEAD

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meet5.userprofile.Service.KafkaUserProfileConsumer;
import com.meet5.userprofile.Service.KafkaUserProfileProducer;
import com.meet5.userprofile.dto.IdDto;
import com.meet5.userprofile.dto.ResponseProfileVisitorList;
import com.meet5.userprofile.dto.UserProfileEvent;
import com.meet5.userprofile.model.ProfileVisits;
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meet5.userprofile.config.UserProfileProducer;
import com.meet5.userprofile.dto.IdDto;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meet5.userprofile.config.UserProfileProducer;
import com.meet5.userprofile.dto.IdDto;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import com.meet5.userprofile.model.UserProfile;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.context.annotation.Profile;
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class UserProfileController {

<<<<<<< HEAD
<<<<<<< HEAD
    /*  @Autowired
      UserProfileService userProfileService;*/
    @Autowired
    private KafkaUserProfileProducer userProfileProducer;

    @Autowired
    private KafkaUserProfileConsumer kafkaUserProfileConsumer;
    public UserProfileController(KafkaUserProfileProducer userProfileProducer) {
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
  /*  @Autowired
    UserProfileService userProfileService;*/
    @Autowired
    private UserProfileProducer userProfileProducer;

    public UserProfileController(UserProfileProducer userProfileProducer) {
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
        this.userProfileProducer = userProfileProducer;
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUserProfile(@RequestBody UserProfile userProfileRequest) throws JsonProcessingException {

        // monolithic way
     /*   int userprofileRes = userProfileService.createUserprofile(userProfile);
        if (userprofileRes > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/

<<<<<<< HEAD
<<<<<<< HEAD
        UserProfileEvent userProfileEvent = new UserProfileEvent();
        userProfileEvent.setMessage("userprofile is in pending state");
        userProfileEvent.setStatus("PENDING");
        userProfileEvent.setUserProfile(userProfileRequest);

        userProfileProducer.sendUserProfileEventMessage(userProfileEvent);
        //userProfileProducer.sendUserProfileRequest(userProfileRequest);
=======
        userProfileProducer.sendUserProfileRequest(userProfileRequest);
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
        userProfileProducer.sendUserProfileRequest(userProfileRequest);
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/insertBulkUsers")
    public String insertMultipleUserProfiles(@RequestBody List<UserProfile> dataList) {
        userProfileProducer.insertBulkData(dataList);
<<<<<<< HEAD
<<<<<<< HEAD
        return "Bulk data insertion in progress.";
    }

    @PostMapping(value = "/user/visit")
    public ResponseEntity<HttpStatus> CreateProfileVisitor(@RequestParam(name = "userId") Long userId,
                                                           @RequestParam(name = "visitorId") Long visitorId) {
        // monolithic way
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
        return "Bulk data inserted successfully.";
    }
   @PostMapping(value = "/user/visit")
    public ResponseEntity<HttpStatus> CreateProfileVisitor(@RequestParam(name = "userId") Long userId,
                                                           @RequestParam(name = "visitorId") Long visitorId)  {
       // monolithic way
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
       /* int userprofileVisitorRes = userProfileService.createUserProfileVisitor(userId, visitorId);
        if (userprofileVisitorRes > 0) {
            log.info("user's profile visit saved");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
<<<<<<< HEAD
<<<<<<< HEAD
        IdDto idDto = IdDto.builder()
                .user(userId)
                .visitor(visitorId)
                .activity("visit")
                .build();
        userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/like")
    public ResponseEntity<HttpStatus> CreateProfileLikes(@RequestParam(name = "userId") Long userId,
                                                         @RequestParam(name = "visitorId") Long likerId) {
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
       IdDto idDto = IdDto.builder()
               .user_id(userId)
               .visitor_id(visitorId)
               .activity("visit")
               .build();
       userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/user/like")
    public ResponseEntity<HttpStatus> CreateProfileLikes(@RequestParam(name = "userId") Long userId,
                                                         @RequestParam(name = "visitorId") Long likerId)  {
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
        // monolithic way
       /* int profileLikes = userProfileService.createUserProfileLikes(userId, likerId);
        if (profileLikes > 0) {
            log.info("user's profile like saved");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
        IdDto idDto = IdDto.builder()
<<<<<<< HEAD
<<<<<<< HEAD
                .user(userId)
                .visitor(likerId)
                .activity("like")
                .build();
        userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/visitors")
    //@CircuitBreaker(name = "visitors", fallbackMethod = "fallback")
    //@TimeLimiter(name = "visitors")
    public ResponseEntity<List<ProfileVisits>> findProfileVisitorsByVisitedProfileId(@RequestParam(name = "userId") Long userId) {
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
                .user_id(userId)
                .visitor_id(likerId)
                .activity("like")
                .build();
        userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @GetMapping("/user/visitors")
   @CircuitBreaker(name="visitors", fallbackMethod="fallback")
   @TimeLimiter(name="visiotrs")
    public ResponseEntity<HttpStatus> findProfileVisitorsByVisitedProfileId(@RequestParam(name = "userId") Long userId)  {
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
        // monolithic way
       /* List<ProfileVisits> profileVisitsList= userProfileService.findProfileVisitorsByUserId(userId);
        if(profileVisitsList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(profileVisitsList, HttpStatus.OK);*/
<<<<<<< HEAD
<<<<<<< HEAD
        IdDto idDto = IdDto.builder()
                .user(userId)
                .activity("fetch")
                .build();
        userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
        List<ProfileVisits> lp =  ResponseProfileVisitorList.getProfileVisitsList();
        log.info("res: " +  lp);

       return new ResponseEntity(lp, HttpStatus.OK);
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
       IdDto idDto = IdDto.builder()
               .user_id(userId)
               .activity("fetch")
               .build();
       userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
       return new ResponseEntity(HttpStatus.OK);
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
    }

    public String fallbackMethod(Throwable t) {

        return "Oops! error occurred. please try again";
    }

}
