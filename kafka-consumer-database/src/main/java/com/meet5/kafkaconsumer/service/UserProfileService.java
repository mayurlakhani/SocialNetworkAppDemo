package com.meet5.kafkaconsumer.service;

<<<<<<< HEAD
<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import com.meet5.kafkaconsumer.model.ProfileVisits;
import com.meet5.kafkaconsumer.utils.ResourceNotFoundException;

import com.meet5.kafkaconsumer.model.UserProfile;
import com.meet5.kafkaconsumer.utils.ValidationException;


import java.util.List;

public interface UserProfileService {

    int createUserprofile(UserProfile userProfile) throws ResourceNotFoundException, ValidationException;

    int createUserProfileVisitor(Long userId, Long visitedId) throws ResourceNotFoundException;

    List<ProfileVisits> findProfileVisitorsByUserId(Long userId) throws ResourceNotFoundException;

    int createUserProfileLikes(Long userId, Long visitorId) throws ResourceNotFoundException;

    List<Integer> markUsersAsFraudulent();

<<<<<<< HEAD
<<<<<<< HEAD
    int bulkDataInsertion(String eventMessage) throws JsonProcessingException;
=======
    void bulkDataInsertion(String eventMessage);
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
    void bulkDataInsertion(String eventMessage);
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
}
