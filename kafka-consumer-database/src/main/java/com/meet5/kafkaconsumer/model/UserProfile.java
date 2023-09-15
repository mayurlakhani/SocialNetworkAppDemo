package com.meet5.kafkaconsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@Data
@Builder
@Getter
@Setter
public class UserProfile {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("age")
    private int age;
    @JsonProperty("isFraudulent")
    private boolean isFraudulent;
    public UserProfile(){}

    public UserProfile(Long userId, String name, String email, int age, boolean isFraudulent) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.isFraudulent = isFraudulent;
    }

    public UserProfile(String name, String email, int age, boolean isFraudulent) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isFraudulent = isFraudulent;
    }

}
