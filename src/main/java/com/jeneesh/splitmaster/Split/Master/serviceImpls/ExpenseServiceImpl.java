package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.entities.*;
import com.jeneesh.splitmaster.Split.Master.repositories.*;
import com.jeneesh.splitmaster.Split.Master.services.ExpenseService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ExpenseResponseDto createSplitManual(Long userId, ExpenseRequestDto expenseRequestDto){
        int members = expenseRequestDto.getPhoneNumbers().size() + 1;
        double totalAmountPaid = 0;
        double hasToPay =
                (expenseRequestDto.getTotalAmount() -
                        expenseRequestDto.getPaidByCreator())
                        / (members - 1);
        System.out.println("The split amount is -> " + hasToPay);
        Set<String> numSet = new HashSet<>();
        if (userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        if (expenseRequestDto == null) {
            throw new RuntimeException("Invalid expense request");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Groups group = groupRepository.findById(expenseRequestDto.getGroupId()).orElseThrow(() ->
                new RuntimeException("Group not found"));
        if (!groupParticipantsRepository.existsByMembersIdAndGroupId(user, group)) {
            throw new RuntimeException("You don't have a group with that group id ");
        }
        if (expenseRequestDto.getTotalAmount() < members) {
            throw new RuntimeException("Minimum 1 Rupee per person is allowed");
        }
        if (totalAmountPaid > expenseRequestDto.getTotalAmount()) {
            throw new RuntimeException("Amount paid by users exceed total amount");
        }
        if (expenseRequestDto.getPaidByCreator() > expenseRequestDto.getTotalAmount() - (members - 1)) {
            throw new RuntimeException("Leave minimum 1 rupee per person");
        }
        if (totalAmountPaid + expenseRequestDto.getPaidByCreator() > expenseRequestDto.getTotalAmount()) {
            throw new RuntimeException("The amount paid by you and the participants exceed te total amount");
        }
        for (ExpenseParticipantsDto num : expenseRequestDto.getPhoneNumbers()) {
            User tempUser = userRepository.findByPhoneNumber(num.getPhoneNumber());
            UserValidation.validPhoneNumber(num.getPhoneNumber());
            totalAmountPaid += num.getAmount();
            if (num.getPhoneNumber().equalsIgnoreCase(user.getPhoneNumber())) {
                throw new RuntimeException("User phone number cannot be in tne participants");
            }
            if (numSet.contains(num.getPhoneNumber())) {
                throw new RuntimeException("You have entered the same number more than once");
            }
            if (num.getAmount() < hasToPay || num.getAmount() > hasToPay) {
                throw new RuntimeException("One of the participants are paying the invalid split amount");
            }
            if (!userRepository.existsByPhoneNumber(num.getPhoneNumber())) {
                throw new RuntimeException("Phone number not found");
            }
            if (!groupParticipantsRepository.existsByMembersIdAndGroupId(tempUser, group)) {
                throw new RuntimeException("One of the numbers you entered is not in your group");
            }
            else {
                numSet.add(num.getPhoneNumber());
            }
        }
        Expense expense = new Expense(group, user, expenseRequestDto.getDesc(), expenseRequestDto.getTotalAmount());
        expenseRepository.save(expense);
        ExpenseParticipants creatorExpense = new ExpenseParticipants(expense, group, user,
                expenseRequestDto.getPaidByCreator(), expenseRequestDto.getPaidByCreator());
        expenseParticipantsRepository.save(creatorExpense);


        for (ExpenseParticipantsDto num : expenseRequestDto.getPhoneNumbers()) {
            User tempUser = userRepository.findByPhoneNumber(num.getPhoneNumber());
            ExpenseParticipants expenseParticipants = new ExpenseParticipants(expense, group, tempUser, num.getAmount(), 0);
            expenseParticipantsRepository.save(expenseParticipants);
            if (groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group, user, tempUser)) {
                GroupBalances creatorGroupBalance = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group, user, tempUser).orElseThrow(() ->
                        new RuntimeException("Group balances not found"));
                double creatorAmount = creatorGroupBalance.getAmount();
                double participantAmount = expenseParticipants.getOwnedAmount();

                if (creatorAmount > participantAmount) {
                    creatorAmount = creatorAmount - participantAmount;
                    creatorGroupBalance.setAmount(creatorAmount);
                    groupBalancesRepository.save(creatorGroupBalance);
                } else if (participantAmount > creatorAmount) {
                    participantAmount = participantAmount - creatorAmount;
                    groupBalancesRepository.delete(creatorGroupBalance);
                    GroupBalances groupBalances = new GroupBalances(group, tempUser, user, participantAmount);
                    groupBalancesRepository.save(groupBalances);
                } else if (creatorAmount == participantAmount) {
                    groupBalancesRepository.delete(creatorGroupBalance);
                }
            } else if (groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group, tempUser, user)) {
                GroupBalances groupBalances = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group, tempUser, user).orElse(null);
                double groupBalancesAmount = groupBalances.getAmount();
                groupBalances.setAmount(groupBalancesAmount + num.getAmount());
                groupBalancesRepository.save(groupBalances);
            } else {
                GroupBalances groupBalances = new GroupBalances(group, tempUser, user, num.getAmount());
                groupBalancesRepository.save(groupBalances);
            }

        }
        return new ExpenseResponseDto(group.getName(), group.getGroupId(), userId,
                expenseRequestDto.getDesc(), expenseRequestDto.getPhoneNumbers());
    }



    @Override
    @Transactional
    public ExpenseAutoSplitResponse createSplitAuto(Long userId, ExpenseRequestDto expenseRequestDto){
        Set<String> numSet = new HashSet<>();
        int members = expenseRequestDto.getContacts().size() + 1;
        double hasToPay =
                (expenseRequestDto.getTotalAmount()
                        - expenseRequestDto.getPaidByCreator())
                        / (members - 1);
        if (userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Groups group = groupRepository.findById(expenseRequestDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
        if (!groupParticipantsRepository.existsByMembersIdAndGroupId(user, group)) {
            throw new RuntimeException("You are not a member of this group");
        }
        if (expenseRequestDto.getPaidByCreator() >= expenseRequestDto.getTotalAmount()) {
            throw new RuntimeException("Leave minimum 1 Rupee per person for split");
        }
        for (ContactRequestDto num : expenseRequestDto.getContacts()) {
            UserValidation.validPhoneNumber(num.getPhoneNumber());
            User tempUser = userRepository.findByPhoneNumber(num.getPhoneNumber());
            if (!userRepository.existsByPhoneNumber(num.getPhoneNumber())) {
                throw new RuntimeException("Phone number not found");
            }
            if (!groupParticipantsRepository.existsByMembersIdAndGroupId(tempUser, group)) {
                throw new RuntimeException("One of the numbers you entered is not in your group");
            }

            if (numSet.contains(num.getPhoneNumber())) {
                throw new RuntimeException("Cannot have duplicates in participants");
            }
            if (num.getPhoneNumber().equals(user.getPhoneNumber())) {
                throw new RuntimeException("User number cannot be in the participants");
            } else {
                numSet.add(num.getPhoneNumber());
            }
        }

        Expense expense = new Expense(group, user, expenseRequestDto.getDesc(), expenseRequestDto.getTotalAmount());
        expenseRepository.save(expense);
        ExpenseParticipants expenseParticipants = new ExpenseParticipants(expense, group, user,
                expenseRequestDto.getPaidByCreator(), expenseRequestDto.getPaidByCreator());
        expenseParticipantsRepository.save(expenseParticipants);

        for (String num : numSet) {
            User temperUser = userRepository.findByPhoneNumber(num);
            ExpenseParticipants expenseParticipant2 = new ExpenseParticipants(expense, group, temperUser, hasToPay, 0);
            expenseParticipantsRepository.save(expenseParticipant2);

            if (groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group, user, temperUser)) {
                GroupBalances creatorGroupBalance = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group, user, temperUser)
                        .orElse(null);

                double creatorAmount = creatorGroupBalance.getAmount();
                double participantAmount = expenseParticipant2.getOwnedAmount();

                if (creatorAmount > participantAmount) {
                    creatorAmount = creatorAmount - participantAmount;
                    creatorGroupBalance.setAmount(creatorAmount);
                    groupBalancesRepository.save(creatorGroupBalance);
                } else if (participantAmount > creatorAmount) {
                    participantAmount = participantAmount - creatorAmount;
                    groupBalancesRepository.delete(creatorGroupBalance);
                    GroupBalances groupBalances = new GroupBalances(group, temperUser, user, participantAmount);
                    groupBalancesRepository.save(groupBalances);
                } else if (creatorAmount == participantAmount) {
                    groupBalancesRepository.delete(creatorGroupBalance);
                }
            } else if (groupBalancesRepository.existsByGroupIdAndPayerIdAndReceiverId(group, temperUser, user)) {
                GroupBalances groupBalances = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(group, temperUser, user)
                        .orElse(null);
                double groupBalancesAmount = groupBalances.getAmount();
                groupBalances.setAmount(groupBalancesAmount + expenseParticipant2.getOwnedAmount());
                groupBalancesRepository.save(groupBalances);
            } else {
                GroupBalances groupBalances = new GroupBalances(group, temperUser, user, expenseParticipant2.getOwnedAmount());
                groupBalancesRepository.save(groupBalances);
            }
        }
        return new ExpenseAutoSplitResponse(group.getName(), group.getGroupId(), userId,expenseRequestDto.getDesc(),expenseRequestDto.getContacts());
    }

    @Override
    @Transactional
    public ExpensePaidResponseDto paySplit(Long userId, ExpensePayRequestDto expensePayRequestDto){
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).orElse(null);
        Groups groups = groupRepository.findById(expensePayRequestDto.getGroupId())
                .orElseThrow(() ->new RuntimeException("Group not found"));
        if(!groupParticipantsRepository.existsByMembersIdAndGroupId(user,groups)){
            throw new RuntimeException("The user does not have a group with the group id");
        }
        Expense expense = expenseRepository.findById(expensePayRequestDto.getExpenseId()).orElseThrow(() ->new RuntimeException("Expense not found"));
        ExpenseParticipants expenseParticipants = expenseParticipantsRepository.findByExpenseIdAndGroupIdAndUserId(expense,groups,user).orElseThrow(() -> new RuntimeException("Participant not found"));

        if(expenseParticipants.getPaidAmount() == expenseParticipants.getOwnedAmount()){
            throw new RuntimeException("You already paid your split amount earlier");
        }

        if(expensePayRequestDto.getAmount() < expenseParticipants.getOwnedAmount() || expensePayRequestDto.getAmount() > expenseParticipants.getOwnedAmount()){
            throw new RuntimeException("Amount cannot be more than or less than the split amount");
        }
        User receiver = userRepository.findById(expense.getCreatedBy()).orElseThrow(() ->new RuntimeException("User not found"));
        expenseParticipants.setPaidAmount(expensePayRequestDto.getAmount());
        expenseParticipantsRepository.save(expenseParticipants);

        GroupBalances groupBalances = groupBalancesRepository.findByGroupIdAndPayerIdAndReceiverId(groups,user,receiver).orElse(null);

        if(groupBalances.getAmount() > expensePayRequestDto.getAmount()){
            double finalAmount = groupBalances.getAmount() - expensePayRequestDto.getAmount();
            groupBalances.setAmount(finalAmount);
            groupBalancesRepository.save(groupBalances);
        }
        else{
            groupBalancesRepository.delete(groupBalances);
        }
        return new ExpensePaidResponseDto(groups.getGroupId(),groups.getName(),expenseParticipants.getOwnedAmount(),
                expenseParticipants.getPaidAmount());
    }

    @Override
    public ExpenseOverallSplitsResDto viewAllSplits(Long userId, SplitsRequestDto splitsRequestDto) {
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("No user profile found for this id"));
        Groups group = groupRepository.findById(splitsRequestDto.getGroupId()).orElseThrow(()->
                new RuntimeException("No groups were found for this groupId"));
        if(!groupParticipantsRepository.existsByMembersIdAndGroupId(user,group)){
            throw new RuntimeException("The user does not have a group with the group id");
        }

        List<ExpenseParticipants>allSplits = expenseParticipantsRepository.findByGroupIdAndUserId(group,user).orElseThrow(()->
                new RuntimeException("No splits were found for this user id or group id"));

        List<ExpenseSplitView>splitList = new ArrayList<>();

        for(ExpenseParticipants expenseParticipants : allSplits){
            Expense tempExp = expenseRepository.findById(expenseParticipants.getExpenseId()).orElse(null);
            ExpenseSplitView splitView = new ExpenseSplitView(expenseParticipants.getExpenseId(), tempExp.getDescription(),
                    tempExp.getTotalAmount(), expenseParticipants.getPaidAmount());
            splitList.add(splitView);
        }
        return new ExpenseOverallSplitsResDto(group.getGroupId(), group.getName(), splitList);
    }

    @Override
    public ExpensePaidResponseDto viewOweAndOwedAmount(Long userId, SplitsRequestDto splitsRequestDto) {
        double owedAmount = 0;
        double oweAmount = 0;
        if (userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No user profile found for this id"));
        Groups groups = groupRepository.findById(splitsRequestDto.getGroupId()).
                orElseThrow(() -> new RuntimeException("No group was found for the entered id"));
        if (!groupParticipantsRepository.existsByMembersIdAndGroupId(user, groups)) {
            throw new RuntimeException("The user does not have a group with the group id");
        }
            List<Optional<GroupBalances>>hasToPay = groupBalancesRepository.findByGroupIdAndPayerId(groups, user);
            for(Optional<GroupBalances> obj : hasToPay){
                owedAmount += obj.get().getAmount();
        }
            List<Optional<GroupBalances>> hasToGet = groupBalancesRepository.findByGroupIdAndReceiverId(groups, user);
            for(Optional<GroupBalances> obj : hasToGet){
                oweAmount += obj.get().getAmount();
            }


        return new ExpensePaidResponseDto(groups.getGroupId(),groups.getName(),owedAmount,oweAmount);
    }




}

