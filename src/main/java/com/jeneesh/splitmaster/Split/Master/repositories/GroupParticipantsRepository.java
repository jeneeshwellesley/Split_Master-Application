package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.GroupParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupParticipantsRepository extends JpaRepository<GroupParticipants,Long> {
    boolean existsByMembersId (User contactUser);
    GroupParticipants findByMembersId (User contactUser);
    List<GroupParticipants> findAllByMembersId (User contactUser);
    boolean existsByMembersIdAndGroupId (User contactUser,Groups group);
    GroupParticipants findByMembersIdAndGroupId(User user,Groups group);
    void deleteByGroupId(Groups group);


}
