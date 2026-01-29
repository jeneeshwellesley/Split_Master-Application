package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.UserRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

        @Autowired
        public UserServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

    @Override
    public UserResponseDto register(UserRequestDto user) {
        if(user == null || user.getPhoneNumber() == null ||user.getPhoneNumber().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank() || user.getPhoneNumber().matches("^[6-9]\\d{9}$")){
            throw new RuntimeException("Invalid user details");
        }

        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new RuntimeException("User already exists");
        }


        else{
            User dbUser = new User(user.getName(),user.getPassword(),user.getPhoneNumber());
            userRepository.save(dbUser);
            return new UserResponseDto(dbUser.getUserId(), dbUser.getUserName(), dbUser.getPhoneNumber());
        }

    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public User viewProfile(User user) {
        return null;
    }

    @Override
    public User updateProfile(User user) {
        return null;
    }
}
