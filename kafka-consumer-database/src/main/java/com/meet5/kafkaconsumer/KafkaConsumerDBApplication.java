package com.meet5.kafkaconsumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class KafkaConsumerDBApplication {
    public static void main(String[] args) {

        SpringApplication.run(KafkaConsumerDBApplication.class, args);
    }
}
