package com.meet5.kafkaconsumer.utils;

<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
=======
import java.util.regex.Pattern;

>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
=======
import java.util.regex.Pattern;

>>>>>>> 18c714e096c248e5644ec21e0b2e26d6587d68d3
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
