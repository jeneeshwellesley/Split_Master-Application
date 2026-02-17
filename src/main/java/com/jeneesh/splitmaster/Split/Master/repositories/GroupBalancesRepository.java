package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.GroupBalances;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupBalancesRepository extends JpaRepository<GroupBalances,Long> {

    boolean existsByGroupIdAndPayerIdAndReceiverId(Groups group, User payer, User receiver);
    Optional<GroupBalances> findByGroupIdAndPayerIdAndReceiverId(Groups group, User payer, User receiver);
}
