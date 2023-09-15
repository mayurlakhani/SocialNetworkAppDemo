package com.meet5.kafkaconsumer.utils;

import java.util.regex.Pattern;

public class UserValidator {
    public boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    public boolean checkUserProfileData(String name, String email){
        if(isEmailValid(email) && isNameValid(name)){
            return true;
        }
        return false;

    }

}
