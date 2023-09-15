package com.meet5.userprofile.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meet5.userprofile.config.UserProfileProducer;
import com.meet5.userprofile.dto.IdDto;
import com.meet5.userprofile.model.UserProfile;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class UserProfileController {

  /*  @Autowired
    UserProfileService userProfileService;*/
    @Autowired
    private UserProfileProducer userProfileProducer;

    public UserProfileController(UserProfileProducer userProfileProducer) {
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

        userProfileProducer.sendUserProfileRequest(userProfileRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/insertBulkUsers")
    public String insertMultipleUserProfiles(@RequestBody List<UserProfile> dataList) {
        userProfileProducer.insertBulkData(dataList);
        return "Bulk data inserted successfully.";
    }
   @PostMapping(value = "/user/visit")
    public ResponseEntity<HttpStatus> CreateProfileVisitor(@RequestParam(name = "userId") Long userId,
                                                           @RequestParam(name = "visitorId") Long visitorId)  {
       // monolithic way
       /* int userprofileVisitorRes = userProfileService.createUserProfileVisitor(userId, visitorId);
        if (userprofileVisitorRes > 0) {
            log.info("user's profile visit saved");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
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
        // monolithic way
       /* int profileLikes = userProfileService.createUserProfileLikes(userId, likerId);
        if (profileLikes > 0) {
            log.info("user's profile like saved");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
        IdDto idDto = IdDto.builder()
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
        // monolithic way
       /* List<ProfileVisits> profileVisitsList= userProfileService.findProfileVisitorsByUserId(userId);
        if(profileVisitsList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(profileVisitsList, HttpStatus.OK);*/
       IdDto idDto = IdDto.builder()
               .user_id(userId)
               .activity("fetch")
               .build();
       userProfileProducer.sendProfileVisitorOrProfileLikerId(idDto);
       return new ResponseEntity(HttpStatus.OK);
    }

    public String fallbackMethod(Throwable t) {

        return "Oops! error occurred. please try again";
    }

}
