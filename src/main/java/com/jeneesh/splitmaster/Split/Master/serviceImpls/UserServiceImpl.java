package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.UserService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

        @Autowired
        public UserServiceImpl(UserRepository userRepository){
            this.userRepository = userRepository;
        }
    @Transactional
    @Override
    public UserResponseDto register(UserRequestDto user) {
            UserValidation.validUserName(user.getName());
            UserValidation.validPassword(user.getPassword());
            UserValidation.validPhoneNumber(user.getPhoneNumber());

        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new RuntimeException("User already exists");
        }


        else{
            User dbUser = new User(user.getName(),user.getPassword(),user.getPhoneNumber());
            userRepository.save(dbUser);
            return new UserResponseDto(dbUser.getUserId(), dbUser.getUserName(),
                    dbUser.getPhoneNumber(), dbUser.getCreatedAt(),dbUser.getUpdatedAt());
        }

    }
    @Transactional
    @Override
    public UserResponseDto login(UserLoginDto user) {
        UserValidation.validPhoneNumber(user.getPhoneNumber());
        UserValidation.validPassword(user.getPassword());

        if(!userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new RuntimeException("User does not exist");
        }
        User newUser = userRepository.findByPhoneNumber(user.getPhoneNumber());

        if(!newUser.getPassword().equals(user.getPassword())){
            throw new  RuntimeException("Passwords do not match");
        }

        return new UserResponseDto(newUser.getUserId(), newUser.getUserName(),
                newUser.getPhoneNumber(), newUser.getCreatedAt(),newUser.getUpdatedAt());
    }

    @Override
    public UserResponseDto viewProfile(Long userId) {
            if(userId == null){
                throw new RuntimeException("Invalid UserId");
            }
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User does not exist"));
            return new UserResponseDto(user.getUserId(), user.getUserName(), user.getPhoneNumber(),
                    user.getCreatedAt(), user.getUpdatedAt());
    }
    @Transactional
    @Override
    public UserResponseDto updateProfile(UserUpdateDto userUpdateDto, Long userId){
            boolean userNameIsPre = false;
            boolean phoneNumberIsPre = false;
        if(userId == null){
            throw new RuntimeException("Invalid UserId");
        }

        if(userRepository.existsByPhoneNumber(userUpdateDto.getPhoneNumber())){
            throw new RuntimeException("Requested Phone Number already exists");
        }


        if(userUpdateDto.getUserName() != null) {
            UserValidation.validUserName(userUpdateDto.getUserName());
            userNameIsPre = true;
        }
        if(userUpdateDto.getPhoneNumber() != null) {
            UserValidation.validPhoneNumber(userUpdateDto.getPhoneNumber());
            phoneNumberIsPre = true;
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User does not exist"));

        if(userNameIsPre){
            user.setUserName(userUpdateDto.getUserName());
        }
        if(phoneNumberIsPre){
            user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        }
        userRepository.save(user);

        return new UserResponseDto(user.getUserId(),user.getUserName(), user.getPhoneNumber(), user.getCreatedAt(), LocalDateTime.now());

    }

    @Override
    public String viewProfilePassword(Long userId) {
        if(userId == null){
            throw new RuntimeException("Invalid UserId");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User does not exist"));
        return "The user password is " + user.getPassword();
    }
    @Transactional
    @Override
    public String updatePassword(UserPasswordUpdateDto userPasswordUpdateDto, Long userId){
        if(userId == null){
            throw new RuntimeException("Invalid UserId");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User does not exist"));

        if(userPasswordUpdateDto.getNewPassword() != null || userPasswordUpdateDto.getOldPassword() != null){
            if(!userPasswordUpdateDto.getNewPassword().equals(userPasswordUpdateDto.getOldPassword())){
                if(user.getPassword().equals(userPasswordUpdateDto.getOldPassword())){
                    user.setPassword(userPasswordUpdateDto.getNewPassword());
                }
                else {
                    throw new RuntimeException("The old password do not match with the current password");
                }
            }
            else{
                throw new RuntimeException("The old password cannot be same as new password");
            }
        }
        else{
            throw new RuntimeException("Invalid old and new passwords");
        }

        userRepository.save(user);
        return "Updated password is " + user.getPassword();
    }

    @Override
    public User viewCompleteUserProfile(Long userId) {
        if(userId == null){
            throw new RuntimeException("Invalid UserId");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User does not exist"));
        return user;
    }
}
