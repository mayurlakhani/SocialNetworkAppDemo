package com.meet5.userprofile;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling

@SpringBootApplication
@EnableEurekaClient
public class UserProfileService {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileService.class,args);
    }
}
