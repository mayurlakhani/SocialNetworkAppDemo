package com.meet5.kafkaconsumer.service;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet5.kafkaconsumer.model.ProfileLikes;
import com.meet5.kafkaconsumer.model.ProfileVisits;
import com.meet5.kafkaconsumer.utils.ResourceNotFoundException;


import com.meet5.kafkaconsumer.model.UserProfile;
import com.meet5.kafkaconsumer.utils.UserValidator;
import com.meet5.kafkaconsumer.utils.ValidationException;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.apache.catalina.User;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.stream.Collectors;
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final JdbcTemplate jdbcTemplate;
<<<<<<< HEAD

    @Autowired
    UserValidator userValidator;

    public UserProfileServiceImpl(DataSource dataSource, UserValidator userValidator) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.userValidator = userValidator;
    }

=======
    private UserValidator userValidator;

    public UserProfileServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
    @Override
    public int createUserprofile(UserProfile userProfile) throws ResourceNotFoundException, ValidationException {

        UserProfile up = null;
        try {
            up = findById(userProfile.getUserId());
        }  // find friend id
        catch (Exception e) {
            log.warn("exception: " + e);
        }

        if (up == null) {
            if (!userValidator.isNameValid(userProfile.getName())) {
                throw new ValidationException("Name is required");
            }
            if (!userValidator.isEmailValid(userProfile.getEmail())) {
                throw new ValidationException("Invalid email format");
            }
            log.info("new profile will be created");
            String sql = "INSERT INTO user_profile(name, email, age, is_fraudulent) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql, userProfile.getName(), userProfile.getEmail(), userProfile.getAge(), false);
        } else {
            String sql = "UPDATE user_profile SET name =?, email=?,age=? WHERE user_id=?";
            log.info("user profile with id " + userProfile.getUserId() + " is updated");
            return jdbcTemplate.update(sql, userProfile.getName(), userProfile.getEmail(), userProfile.getAge(), userProfile.getUserId());
        }
    }

    @Override
    public int createUserProfileVisitor(Long userId, Long visitorId) throws ResourceNotFoundException {
        UserProfile up = findById(userId);   // find user id
        int result = -1;
        ProfileVisits pv = null;
        try {
            pv = findProfileVisitsById(visitorId);
        }  // find friend id
        catch (Exception e) {
            log.warn("exception: " + e);
        }

        if (pv == null) {
            ProfileVisits profileVisits = new ProfileVisits();
            profileVisits.setVisitorId(visitorId);      //  friend
            profileVisits.setVisitedProfileId(up.getUserId());  // user
            profileVisits.setVisitTimestamp(Calendar.getInstance().getTime());
            String query = "INSERT INTO profile_visits(visitor_id, visited_profile_id, visit_timestamp, visit_counter) VALUES (?,?,?,?)";
            log.info(profileVisits.getVisitorId() + " has visited the profile");
            return result = jdbcTemplate.update(query, profileVisits.getVisitorId(), profileVisits.getVisitedProfileId(), profileVisits.getVisitTimestamp(), profileVisits.setVisitCounter(1));

        }
        String query = "UPDATE profile_visits SET visitor_id=?,visited_profile_id=?,"
                + "visit_timestamp=?, visit_counter=? WHERE visitor_id=?";

        return result = jdbcTemplate.update(query, visitorId, userId,
                Calendar.getInstance().getTime(), pv.setVisitCounter(1), visitorId);
    }

    @Override
    public int createUserProfileLikes(Long userId, Long visitorId) throws ResourceNotFoundException {
        UserProfile up = findById(userId);   // find user id
        int result = -1;

        ProfileLikes pl = null;
        try {
            pl = findProfileLikesById(visitorId);
            int create_profile_visit = createUserProfileVisitor(userId, visitorId);
        } catch (Exception e) {
            log.warn("exception: " + e);
        }
        if (pl == null) {
            ProfileLikes profileLikes = new ProfileLikes();
            profileLikes.setLiked_profile_id(userId);
            profileLikes.setLikerId(visitorId);
            profileLikes.setLikedTimestamp(Calendar.getInstance().getTime());
            profileLikes.setLikes(1);
            String query = "INSERT INTO liked_profiles(liker_id, liked_profile_id,like_timestamp, likes) VALUES (?,?,?,?)";
            return jdbcTemplate.update(query, profileLikes.getLikerId(), profileLikes.getLiked_profile_id(), profileLikes.getLikedTimestamp(), profileLikes.getLikes());
        }
        String query = "UPDATE liked_profiles SET liker_id=?,liked_profile_id=?,"
                + "like_timestamp=?, likes=? WHERE liker_id=?";
        return jdbcTemplate.update(query, pl.getLikerId(), pl.getLiked_profile_id(), Calendar.getInstance().getTime(), pl.setLikes(1), visitorId);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 60000)  // 1M= 60s * 1000ms
    public List<Integer> markUsersAsFraudulent() {
        List<Integer> rowsAffected = null;
        try {
            String sql = "UPDATE user_profile u " +
                    "SET u.is_fraudulent = true " +
                    "WHERE u.user_id IN (" +
                    "  SELECT liked_profiles.liker_id " +
                    "  FROM liked_profiles " +
                    "  INNER JOIN profile_visits" +
                    "  ON profile_visits.visitor_id =liked_profiles.liker_id" +
                    "  WHERE profile_visits.visit_timestamp" +
                    " <= NOW()" +
                    " AND profile_visits.visit_timestamp >= DATE_SUB(NOW(), INTERVAL 10 MINUTE)" +
                    " AND liked_profiles.like_timestamp" +
                    " <= NOW()" +
                    " AND liked_profiles.like_timestamp >= DATE_SUB(NOW(), INTERVAL 10 MINUTE)" +
                    " AND liked_profiles.likes >= 100 AND profile_visits.visit_counter >=100" +
                    ")";
            rowsAffected = Collections.singletonList(jdbcTemplate.update(sql));
            log.info("rows are updated:" + rowsAffected);
        } catch (RuntimeException e) {
            log.warn("exception:" + e);
        }
        return rowsAffected;
    }

    @Override
<<<<<<< HEAD
    public int bulkDataInsertion(String profileListObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Profile List Obj: " + profileListObj);
        List<UserProfile> UserProfileList = objectMapper.readValue(profileListObj, new TypeReference<List<UserProfile>>() {
        });

        int[] updateCounts = new int[UserProfileList.size() -1];
        try {
            String sql = "INSERT INTO user_profile(name, email, age, is_fraudulent) VALUES (?, ?, ?, false)";

            updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    UserProfile up = UserProfileList.get(i);
                    preparedStatement.setString(1, up.getName());
                    preparedStatement.setString(2, up.getEmail());
                    preparedStatement.setInt(3, up.getAge());
=======
    public void bulkDataInsertion(String profileListObj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<UserProfile> profileList = objectMapper.readValue(profileListObj, new TypeReference<List<UserProfile>>() {
            });

            // Now, personList contains the deserialized objects
            for (UserProfile up : profileList) {
                System.out.println(up);
            }
            String sql = "INSERT INTO user_profile(name, email, age, is_fraudulent) VALUES (?, ?, ?, ?)";

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    UserProfile up = profileList.get(i);
                    preparedStatement.setString(1, up.getName());
                    preparedStatement.setString(2, up.getEmail());
                    preparedStatement.setInt(3, up.getAge());
                    preparedStatement.setBoolean(4, false);

>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
                }

                @Override
                public int getBatchSize() {
<<<<<<< HEAD
                    return UserProfileList.size();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCounts.length;
=======
                    return profileList.size();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
    }

    private ProfileLikes findProfileLikesById(Long visitorId) throws ResourceNotFoundException {
        String sql = "SELECT * From liked_profiles where liker_id =?";
        ProfileLikes profileLikes_ = jdbcTemplate.query(sql, new Object[]{visitorId},
                (rs, rowNum) -> {
                    ProfileLikes profileLikes = new ProfileLikes();
                    profileLikes.setLikerId(rs.getLong("liker_id"));
                    profileLikes.setLiked_profile_id(rs.getLong("liked_profile_id"));
                    profileLikes.setLikeId(rs.getLong("like_id"));
                    profileLikes.setLikes(rs.getInt("likes"));
                    profileLikes.setLikedTimestamp(rs.getTimestamp("like_timestamp"));
                    return profileLikes;
                }).stream().findFirst().orElse(null);
        if (profileLikes_ == null) {
            throw new ResourceNotFoundException("profile like not found with Id: " + visitorId);
        }
        return profileLikes_;
    }

    public UserProfile findById(Long userId) throws ResourceNotFoundException {

        String sql = "SELECT * From user_profile where user_id=?";

        UserProfile userProfile_ = jdbcTemplate.query(sql, new Object[]{userId},
                (rs, rowNum) -> {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setUserId(rs.getLong("user_id"));
                    userProfile.setAge(rs.getInt("age"));
                    userProfile.setEmail(rs.getString("email"));
                    userProfile.setName(rs.getString("name"));
                    return userProfile;
                }
        ).stream().findFirst().orElse(null);
        if (userProfile_ == null) {
            throw new ResourceNotFoundException("user profile not found with Id: " + userId);
        }
        return userProfile_;
    }

/*    public List<ProfileVisits> findProfileVisitorsByVisitedProfileId(Long userId) throws ResourceNotFoundException {
        String sql = "SELECT * From profile_visits where visited_profile_id=" + userId;

        List<ProfileVisits> profileVisitsList = jdbcTemplate.query(sql, new RowMapper<ProfileVisits>() {
            @Override
            public ProfileVisits mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProfileVisits profileVisits = new ProfileVisits();
                profileVisits.setVisitCounter(rs.getInt("visit_counter"));
                profileVisits.setVisitorId(rs.getLong("visitor_id"));
                profileVisits.setVisitTimestamp(rs.getTimestamp("visit_timestamp"));
                profileVisits.setVisitedProfileId(rs.getLong("visited_profile_id"));
                return profileVisits;
            }
        }).stream().collect(Collectors.toList());
        if (profileVisitsList.isEmpty()) {
            throw new ResourceNotFoundException("visitor id " + userId + " not found");
        }
        log.info("ProfileVisitorsBy user:" + profileVisitsList.toString());
        return profileVisitsList;
    }*/

    public ProfileVisits findProfileVisitsById(Long visitorId) throws ResourceNotFoundException {
        String sql = "SELECT * From profile_visits where visitor_id=?";  // friend

        ProfileVisits profileVisits_ = jdbcTemplate.query(sql, new Object[]{visitorId},
                (rs, rowNum) -> {
                    ProfileVisits profileVisits = new ProfileVisits();
                    profileVisits.setVisitId(rs.getLong("visit_id"));
                    profileVisits.setVisitedProfileId(rs.getLong("visited_profile_id"));
                    profileVisits.setVisitorId(rs.getLong("visitor_id"));
                    profileVisits.setVisitTimestamp(rs.getTimestamp("visit_timestamp"));
                    profileVisits.setVisitCounter(rs.getInt("visit_counter"));
                    log.info("profileVisits_: " + profileVisits.toString());
                    return profileVisits;
                }
        ).stream().findFirst().orElse(null);
        if (profileVisits_ == null) {
            throw new ResourceNotFoundException("visitor profile not found with ID: " + visitorId);
        }
        return profileVisits_;
    }

    public List<ProfileVisits> findProfileVisitorsByUserId(Long userId) throws ResourceNotFoundException {
<<<<<<< HEAD
        String sql = "SELECT * From profile_visits WHERE visited_profile_id=" + userId + " ORDER BY visit_timestamp DESC";
=======
        String sql = "SELECT * From profile_visits WHERE visited_profile_id=" + userId+ "ORDER BY visit_timestamp DESC";
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3

        List<ProfileVisits> profileVisitsList = jdbcTemplate.query(sql, new RowMapper<ProfileVisits>() {
            @Override
            public ProfileVisits mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProfileVisits profileVisits = new ProfileVisits();
                profileVisits.setVisitId(rs.getLong("visit_id"));
                profileVisits.setVisitedProfileId(rs.getLong("visited_profile_id"));
                profileVisits.setVisitorId(rs.getLong("visitor_id"));
                profileVisits.setVisitTimestamp(rs.getTimestamp("visit_timestamp"));
                profileVisits.setVisitCounter(rs.getInt("visit_counter"));
                log.info("profileVisits: " + profileVisits.toString());
                return profileVisits;
            }
        });
        if (profileVisitsList.isEmpty()) {
            throw new ResourceNotFoundException("visitor id " + userId + " not found");
        }
        log.info("ProfileVisitors :" + profileVisitsList.toString() + ", per user: " + userId);
        return profileVisitsList;
    }
}



