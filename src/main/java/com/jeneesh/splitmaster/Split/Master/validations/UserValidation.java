package com.jeneesh.splitmaster.Split.Master.validations;

import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidation {
    private UserRepository userRepository;
    @Autowired
    public UserValidation (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static boolean validPhoneNumber(String ph) {
        if (ph == null || ph.isBlank() || ph.isEmpty() || !ph.matches("^[6-9]\\d{9}$")) {
            throw new RuntimeException("Invalid Phone Number");
        } else {
            return true;
        }
    }

        public static boolean validUserName(String name) {
            if (name == null || name.isBlank() || name.isEmpty() || !name.matches("^[A-Za-z ]+$")) {
                throw new RuntimeException("Invalid Username");
            } else {
                return true;
            }
        }

    public static boolean validPassword(String word){
        if (word == null || word.isBlank() || word.isEmpty()) {
            throw new RuntimeException("Invalid Password");
        } else {
            return true;
        }
    }






    }

