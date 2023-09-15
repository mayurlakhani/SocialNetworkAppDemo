package com.meet5.kafkaconsumer.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TableCreationService {
    public void createTables(JdbcTemplate jdbcTemplate) {
        //  CREATE TABLE statements
        String createUserProfileTable = "CREATE TABLE IF NOT EXISTS user_profile ("
                + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "email VARCHAR(255),"
                + "age INT,"
                + "is_fraudulent BOOLEAN)";

        String createProfileLikes = "CREATE TABLE IF NOT EXISTS liked_profiles ("
                + "like_id INT PRIMARY KEY AUTO_INCREMENT,"
                + "liker_id INT NOT NULL,"
                + "liked_profile_id INT NOT NULL,"
                + "like_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "likes INT NOT NULL,"
                + "FOREIGN KEY (liker_id) REFERENCES user_profile(user_id),"
                + "FOREIGN KEY (liked_profile_id) REFERENCES user_profile(user_id))";

        String createProfileVisits = "CREATE TABLE IF NOT EXISTS profile_visits ("
                + "visit_id INT PRIMARY KEY AUTO_INCREMENT,"
                + "visitor_id INT NOT NULL,"
                + "visited_profile_id INT NOT NULL,"
                + "visit_timestamp TIMESTAMP,"
                + "visit_counter INT,"
                + "FOREIGN KEY (visitor_id) REFERENCES user_profile(user_id),"
                + "FOREIGN KEY (visited_profile_id) REFERENCES user_profile(user_id))";

        jdbcTemplate.execute(createUserProfileTable);
        jdbcTemplate.execute(createProfileVisits);
        jdbcTemplate.execute(createProfileLikes);

    }
}
