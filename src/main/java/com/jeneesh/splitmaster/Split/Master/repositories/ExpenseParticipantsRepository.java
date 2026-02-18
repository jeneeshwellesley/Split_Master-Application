package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.Expense;
import com.jeneesh.splitmaster.Split.Master.entities.ExpenseParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseParticipantsRepository extends JpaRepository<ExpenseParticipants,Long> {
    Optional<ExpenseParticipants> findByExpenseIdAndGroupIdAndUserId(Expense expenseId, Groups groupId, User userId);
    Optional<List<ExpenseParticipants>> findByGroupIdAndUserId(User userId,Groups groupId);




}
