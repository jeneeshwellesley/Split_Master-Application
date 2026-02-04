package com.jeneesh.splitmaster.Split.Master.restControllers;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public UserResponseDto saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.register(userRequestDto);

    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }

    @GetMapping("/view-profile/{userId}")
    public UserResponseDto viewProfile(@PathVariable Long userId){
        return userService.viewProfile(userId);
    }

    @PatchMapping("/update-profile/{userId}")
    public UserResponseDto updateProfile(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto){
        return userService.updateProfile(userUpdateDto, userId);
    }

    @GetMapping("/view-profile-password/{userId}")
    public String viewProfilePassword(@PathVariable Long userId){
        return userService.viewProfilePassword(userId);
    }
    @PatchMapping("/update-profile-password/{userId}")
    public String updateProfilePassword(@PathVariable Long userId, @RequestBody UserPasswordUpdateDto userPasswordUpdateDto){
        return userService.updatePassword(userPasswordUpdateDto,userId);
    }
    @GetMapping("/view-complete-profile/{userId}")
    public User viewCompleteProfile(@PathVariable Long userId){
        return userService.viewCompleteUserProfile(userId);
    }

    }

