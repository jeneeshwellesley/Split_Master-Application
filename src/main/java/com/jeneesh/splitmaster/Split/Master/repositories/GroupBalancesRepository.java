package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.GroupBalances;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.Optional;

public interface GroupBalancesRepository extends JpaRepository<GroupBalances,Long> {
    Optional<GroupBalances>findByGroupIdAndPayerIdAndReceiverId(Groups groupId, User payerId, User receiverId);
    boolean existsByGroupIdAndPayerIdAndReceiverId(Groups groupId, User payerId,User receiverId);
    Optional<GroupBalances>findByGroupIdAndReceiverIdAndPayerId(Groups groupId,User receiverId, User payerId);
    boolean existsByGroupIdAndReceiverIdAndPayerId(Groups groupId, User receiverId,User payerId);
    Optional<GroupBalances>findByGroupIdAndPayerId(Groups groupId, User payerId);
    Optional<GroupBalances>findByGroupIdAndReceiverId(Groups groupId, User receiverId);

}
