package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.ExpenseParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.GroupParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseParticipantsRepository extends JpaRepository<ExpenseParticipants,Long> {

}
