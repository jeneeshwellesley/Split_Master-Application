package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.ExpenseParticipantsDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.Expense;
import com.jeneesh.splitmaster.Split.Master.entities.ExpenseParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.*;
import com.jeneesh.splitmaster.Split.Master.services.ExpenseService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private GroupParticipantsRepository groupParticipantsRepository;
    private ExpenseRepository expenseRepository;
    private ContactRepository contactRepository;
    private ExpenseParticipantsRepository expenseParticipantsRepository;




    @Autowired
    public ExpenseServiceImpl(UserRepository userRepository,GroupRepository groupRepository,
                              ExpenseRepository expenseRepository,
                              ContactRepository contactRepository,
                              ExpenseParticipantsRepository expenseParticipantsRepository,
                              GroupParticipantsRepository groupParticipantsRepository){
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.contactRepository = contactRepository;
        this.expenseParticipantsRepository = expenseParticipantsRepository;
        this.groupParticipantsRepository = groupParticipantsRepository;
    }
    @Override
    public ExpenseResponseDto createSplit(Long userId,ExpenseRequestDto expenseRequestDto){
        return null;
    }



}
