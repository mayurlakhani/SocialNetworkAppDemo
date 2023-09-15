package com.meet5.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
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
}
