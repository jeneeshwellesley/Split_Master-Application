package com.jeneesh.splitmaster.Split.Master.restControllers;

import com.jeneesh.splitmaster.Split.Master.dto.UserRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
