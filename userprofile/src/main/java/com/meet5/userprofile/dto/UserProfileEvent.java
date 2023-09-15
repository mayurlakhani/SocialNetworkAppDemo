package com.meet5.userprofile.dto;

import com.meet5.userprofile.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEvent {

    private  String message;
    private String status;
    private UserProfile userProfile;
}
