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
    public ExpenseResponseDto createSplit(Long userId, ExpenseRequestDto expenseRequestDto) {
        if(userId == null || !userRepository.existsById(userId)){
            throw new RuntimeException("Invalid User id");
        }
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User does not exist"));
        Groups group = groupRepository.findById(expenseRequestDto.getGroupId()).orElseThrow(()
                -> new RuntimeException("Group does not exist with the given group id"));
        if(!groupRepository.existsByGroupIdAndCreatedBy(group.getGroupId(), user)){
            throw new RuntimeException("You don't have a group related to the group id");
        }

        if(expenseRequestDto.getTotalAmount() <=0){
            throw new RuntimeException("Invalid total amount");
        }
        Expense expense = new Expense(group,user, expenseRequestDto.getDesc(),
                expenseRequestDto.getTotalAmount());
        expenseRepository.save(expense);
        int members = 1;

        for(ExpenseParticipantsDto numbers : expenseRequestDto.getPhoneNumbers()){
            members++;
        }
        double amount = expense.getTotalAmount() / members;

        boolean checkedUser = false;
        for(ExpenseParticipantsDto num : expenseRequestDto.getPhoneNumbers()){
            if(userId == expense.getCreatedBy() && !checkedUser){
                ExpenseParticipants expenseParticipants = new ExpenseParticipants(expense,
                        user,0,amount,0,1);
                expenseParticipantsRepository.save(expenseParticipants);
                checkedUser = true;
            }
            else{
                User tempUser = userRepository.findByPhoneNumber(num.getPhoneNumber());
                UserValidation.validPhoneNumber(num.getPhoneNumber());
                if(!contactRepository.existsByUserAndContact(user,tempUser)){
                    throw new RuntimeException("One of the numbers is not in your contact list");
                }
                if(!groupParticipantsRepository.existsByMembersIdAndGroupId(tempUser,group)){
                    throw new RuntimeException("One of the numbers you entered is not in your group");
                }

                else {
                    ExpenseParticipants expenseParticipants = new ExpenseParticipants(expense,
                            tempUser, amount, 0, amount, 0);
                    expenseParticipantsRepository.save(expenseParticipants);
                }
            }
        }

        return new ExpenseResponseDto(group.getName(), group.getGroupId(),user.getUserId(), expense.getDescription(), expenseRequestDto.getPhoneNumbers());
    }
}
