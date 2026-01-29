package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.dto.UserResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);


}
