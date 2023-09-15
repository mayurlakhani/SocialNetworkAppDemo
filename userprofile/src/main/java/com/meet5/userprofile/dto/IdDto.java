package com.meet5.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
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


}
