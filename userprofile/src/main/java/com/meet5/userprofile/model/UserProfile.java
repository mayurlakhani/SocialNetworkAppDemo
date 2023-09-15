
package com.meet5.userprofile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
    private Long userId;
    private String name;
    private String email;
    private int age;
    private boolean isFraudulent;


}

