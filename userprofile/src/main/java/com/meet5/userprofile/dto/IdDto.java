package com.meet5.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.*;
=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3

@Data
@Getter
@Setter
@Builder
<<<<<<< HEAD
<<<<<<< HEAD
@ToString
public class IdDto {

    private Long user;

    private Long visitor;

    private String activity;

    public IdDto(Long userId, Long visitorId, String activity) {
        this.user = userId;
        this.visitor = visitorId;
        this.activity = activity;
    }

    public IdDto() {
    }


=======
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
public class IdDto {
    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("visitor_id")
    private Long visitor_id;

    @JsonProperty("acitivity")
    private String activity;

    public IdDto(){}

    public IdDto(Long user_id, Long visitor_id, String activity) {
        this.user_id = user_id;
        this.visitor_id = visitor_id;
        this.activity = activity;
    }
<<<<<<< HEAD
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
}
