package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.UserLoginDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.UserService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

        @Autowired
        public UserServiceImpl(UserRepository userRepository){
            this.userRepository = userRepository;
        }

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

    @Override
    public UserResponseDto login(UserLoginDto user) {
        UserValidation.validPhoneNumber(user.getPhoneNumber());
        UserValidation.validPassword(user.getPassword());

        if(!userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new RuntimeException("User does not exist");
        }
        User newUser = userRepository.findByPhoneNumber(user.getPhoneNumber());

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

    @Override
    public User updateProfile(User user){
        return null;
    }
}
