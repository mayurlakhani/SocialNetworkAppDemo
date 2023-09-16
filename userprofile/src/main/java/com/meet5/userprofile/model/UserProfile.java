
package com.meet5.userprofile.model;

<<<<<<< HEAD
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
<<<<<<< HEAD
@JsonIgnoreProperties(ignoreUnknown = true)
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
public class UserProfile {
    private Long userId;
    private String name;
    private String email;
    private int age;
    private boolean isFraudulent;


}

