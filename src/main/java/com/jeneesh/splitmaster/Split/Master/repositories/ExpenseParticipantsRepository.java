package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.ExpenseParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.GroupParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseParticipantsRepository extends JpaRepository<ExpenseParticipants,Long> {

}
