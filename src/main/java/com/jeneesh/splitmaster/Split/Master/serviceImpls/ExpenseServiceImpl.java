package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.ExpenseParticipantsDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.*;
import com.jeneesh.splitmaster.Split.Master.repositories.*;
import com.jeneesh.splitmaster.Split.Master.services.ExpenseService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private GroupParticipantsRepository groupParticipantsRepository;
    private ExpenseRepository expenseRepository;
    private ContactRepository contactRepository;
    private ExpenseParticipantsRepository expenseParticipantsRepository;
    private GroupBalancesRepository groupBalancesRepository;




    @Autowired
    public ExpenseServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
                              ExpenseRepository expenseRepository,
                              ContactRepository contactRepository,
                              ExpenseParticipantsRepository expenseParticipantsRepository,
                              GroupParticipantsRepository groupParticipantsRepository,
                              GroupBalancesRepository groupBalancesRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.contactRepository = contactRepository;
        this.expenseParticipantsRepository = expenseParticipantsRepository;
        this.groupParticipantsRepository = groupParticipantsRepository;
        this.groupBalancesRepository = groupBalancesRepository;
    }

    @Transactional
    @Override
    public ExpenseResponseDto createSplitManual(Long userId,ExpenseRequestDto expenseRequestDto){
        int members = expenseRequestDto.getPhoneNumbers().size() + 1;
        int totalAmountPaid = 0;
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(expenseRequestDto == null){
            throw new RuntimeException("Invalid expense request");
        }
        Groups group = groupRepository.findById(expenseRequestDto.getGroupId()).orElseThrow(() ->
                new RuntimeException("Group not found"));
        if(!groupRepository.existsByGroupIdAndCreatedBy(expenseRequestDto.getGroupId(), user)){
            throw new RuntimeException("You don't have a group with that group id ");
        }

        if(expenseRequestDto.getTotalAmount() < members){
            throw new  RuntimeException("Minimum 1 Rupee per person is allowed");
        }
        for(ExpenseParticipantsDto num : expenseRequestDto.getPhoneNumbers()){
            totalAmountPaid += num.getAmount();
        }
        if(totalAmountPaid > expenseRequestDto.getTotalAmount()){
            throw new RuntimeException("Amount paid by users exceed total amount");
        }
        if(expenseRequestDto.getPaidByCreator() > expenseRequestDto.getTotalAmount() - (members - 1)){
            throw new RuntimeException("Leave minimum 1 rupee per person");
        }

        Expense expense = new Expense(group,user,expenseRequestDto.getDesc(),expenseRequestDto.getTotalAmount());
        expenseRepository.save(expense);


        for(ExpenseParticipantsDto num : expenseRequestDto.getPhoneNumbers()){
            UserValidation.validPhoneNumber(num.getPhoneNumber());
            if(!userRepository.existsByPhoneNumber(num.getPhoneNumber())){
                throw new  RuntimeException("Phone number not found");
            }
            User tempUser = userRepository.findByPhoneNumber(num.getPhoneNumber());
            if(!contactRepository.existsByUserAndContact(user,tempUser)){
                throw new RuntimeException("One of the numbers is not in your contact list");
            }
            if(!groupParticipantsRepository.existsByMembersIdAndGroupId(tempUser,group)){
                throw new RuntimeException("One of the numbers you entered is not in your group");
            }
            ExpenseParticipants expenseParticipants = new ExpenseParticipants(expense,group,tempUser, num.getAmount(), 0);

            if(groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group,user,tempUser)){
                GroupBalances creatorGroupBalance = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group,user,tempUser).orElseThrow(() ->
                        new RuntimeException("Group balances not found"));
                double creatorAmount = creatorGroupBalance.getAmount();
                double participantAmount = expenseParticipants.getOwnedAmount();

                if(creatorAmount > participantAmount){
                    creatorAmount = creatorAmount - participantAmount;
                    creatorGroupBalance.setAmount(creatorAmount);
                    groupBalancesRepository.save(creatorGroupBalance);
                }
                else if(participantAmount > creatorAmount){
                    participantAmount = participantAmount - creatorAmount;
                    groupBalancesRepository.delete(creatorGroupBalance);
                    GroupBalances groupBalances = new GroupBalances(group,tempUser,user,participantAmount);
                    groupBalancesRepository.save(groupBalances);
                }
                else if(creatorAmount  ==  participantAmount){
                    groupBalancesRepository.delete(creatorGroupBalance);
                }
            }
            else if(groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group,tempUser,user)){
                GroupBalances groupBalances = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group,tempUser,user).orElse(null);
                double groupBalancesAmount = groupBalances.getAmount();
                groupBalances.setAmount(groupBalancesAmount + num.getAmount());
                groupBalancesRepository.save(groupBalances);
            }
            else{
                GroupBalances groupBalances = new GroupBalances(group,tempUser,user,num.getAmount());
                groupBalancesRepository.save(groupBalances);
            }

        }
        return new ExpenseResponseDto(group.getName(),group.getGroupId(),userId,
                expenseRequestDto.getDesc(),expenseRequestDto.getPhoneNumbers());

    }



}
