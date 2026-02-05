package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.Contacts;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contacts,Long> {
    boolean existsByUserAndContact(User user, User contact);
    Contacts findByUserAndContact(User user, User contact);
    List<Contacts> findByUser_UserId(Long userId);


}
