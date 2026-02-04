package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.entities.User;

public interface UserService {
    UserResponseDto register(UserRequestDto user);
    UserResponseDto login(UserLoginDto user);
    UserResponseDto viewProfile(Long userId);
    UserResponseDto updateProfile(UserUpdateDto user, Long userId);
    String viewProfilePassword(Long userId);
    String updatePassword(UserPasswordUpdateDto userPasswordUpdateDto, Long userId);
    User viewCompleteUserProfile(Long userId);


}
