package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.UserRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;

public interface UserService {
    UserResponseDto register(UserRequestDto user);
    User login(User user);
    User viewProfile(User user);
    User updateProfile(User user);

}
