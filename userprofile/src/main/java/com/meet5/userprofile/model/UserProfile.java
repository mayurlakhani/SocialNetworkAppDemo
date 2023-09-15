
package com.meet5.userprofile.model;

import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Long userId;
    private String name;
    private String email;
    private int age;
    private boolean isFraudulent;


}

