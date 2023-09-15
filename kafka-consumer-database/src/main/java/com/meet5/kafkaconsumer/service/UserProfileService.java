package com.meet5.kafkaconsumer.service;

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

    void bulkDataInsertion(String eventMessage);
}
