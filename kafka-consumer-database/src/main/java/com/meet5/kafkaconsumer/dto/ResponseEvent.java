package com.meet5.kafkaconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private String response_for;
    private String event;
    private String status;
}
