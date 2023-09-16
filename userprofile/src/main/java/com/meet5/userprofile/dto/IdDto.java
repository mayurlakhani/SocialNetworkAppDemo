package com.meet5.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Builder
@ToString
public class IdDto {

    private Long user;


    private Long visitor;


    private String activity;

    public IdDto(){}

    public IdDto(Long user_id, Long visitor_id, String activity) {
        this.user = user_id;
        this.visitor = visitor_id;
        this.activity = activity;
    }
}