package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.UserLoginDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;

public interface UserService {
    UserResponseDto register(UserRequestDto user);
    UserResponseDto login(UserLoginDto user);
    UserResponseDto viewProfile(Long userId);
    User updateProfile(User user);

}
