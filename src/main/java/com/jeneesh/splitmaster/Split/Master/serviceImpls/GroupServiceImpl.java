package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.entities.GroupParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.ContactRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.GroupParticipantsRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.GroupRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.GroupService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private ContactRepository contactRepository;
    private GroupParticipantsRepository groupParticipantsRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository,
                            ContactRepository contactRepository,GroupParticipantsRepository groupParticipantsRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.groupParticipantsRepository = groupParticipantsRepository;
    }
    @Transactional
    @Override
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto,Long userId) {
        if(userId==null){
            throw new NullPointerException("userId is null");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User does not exist"));
        UserValidation.validUserName(groupRequestDto.getGroupName());
        Groups group = new Groups(groupRequestDto.getGroupName(), user);
        GroupParticipants groupParticipants = new GroupParticipants(group,user,"ADMIN");
        groupRepository.save(group);
        groupParticipantsRepository.save(groupParticipants);
        return new GroupResponseDto(group.getName(), userId);
    }
    @Transactional
    @Override
    public GroupResponseDto deleteGroup(Long userId, Long groupId) {
        if(userId==null){
            throw new NullPointerException("userId is null");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("The user does not exist"));
        if(groupId==null){
            throw new NullPointerException("groupId is null");
        }
        Groups group = groupRepository.findById(groupId).orElseThrow(() -> new  RuntimeException("Group does not exist"));
        if(!groupParticipantsRepository.existsByMembersIdAndGroupId(user,group)){
            throw new RuntimeException("You are not a member of this group");
        }
        GroupParticipants groupParticipant = groupParticipantsRepository.findByMembersIdAndGroupId(user,group);
        if(!groupParticipant.getRole().equals("ADMIN")){
            throw new RuntimeException("You are not the admin to delete this group");
        }


        groupParticipantsRepository.deleteByGroupId(group);
        groupRepository.delete(group);

        return new GroupResponseDto(group.getName(), group.getCreatedBy());
    }

    @Transactional
    @Override
    public GroupParticipantsDto addContactToGroup(Long userId, ContactRequestDto contactRequestDto,Long groupId) {
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).
                orElseThrow(()->new RuntimeException("The user is not registered in the application"));

        UserValidation.validPhoneNumber(contactRequestDto.getPhoneNumber());
        if(!userRepository.existsByPhoneNumber(contactRequestDto.getPhoneNumber())){
            throw new RuntimeException("The entered phone number is not a user of this application");
        }
        User contactUser = userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber());

        if(!contactRepository.existsByUserAndContact(user,contactUser)){
            throw new RuntimeException("The entered contact is not in your contact list");
        }
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("The group id does not exist to add a contact"));
        if(!groupRepository.existsByGroupIdAndCreatedBy(groupId,user)){
            throw new RuntimeException("The entered group id is not yours");
        }

        if(groupParticipantsRepository.existsByMembersIdAndGroupId(contactUser,group)){
            throw new RuntimeException("The entered contact already exists in the group");
        }



        GroupParticipants groupParticipants = new GroupParticipants(group,contactUser,"MEMBER");
        groupParticipantsRepository.save(groupParticipants);
        return new GroupParticipantsDto(group.getGroupId(),group.getName(),
                contactUser.getUserId(),contactUser.getUserName(),"MEMBER");

    }
    @Transactional
    @Override
    public GroupParticipantsDto removeContactFromGroup(Long userId, ContactRequestDto contactRequestDto,Long groupId) {
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }
        if (!userRepository.existsById(userId)){
            throw new NullPointerException("User does not exist");
        }
        UserValidation.validPhoneNumber(contactRequestDto.getPhoneNumber());
        if(!userRepository.existsByPhoneNumber(contactRequestDto.getPhoneNumber())){
            throw new RuntimeException("The entered phone number is not a user of this application");
        }
        User contactUser = userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber());
        if(groupId==null){
            throw new RuntimeException("The group id does not exist to remove a contact");
        }
        Groups group = groupRepository.findById(groupId).orElseThrow(()->new RuntimeException("The group does not exist"));
        if(!groupParticipantsRepository.existsByMembersId(contactUser)){
            throw new RuntimeException("The entered contact is not in your group");
        }
        GroupParticipants groupParticipants= groupParticipantsRepository.findByMembersId(contactUser);
        groupParticipantsRepository.delete(groupParticipants);
        return new GroupParticipantsDto(group.getGroupId(),group.getName(),contactUser.getUserId(),contactUser.getUserName(),"MEMBER");

    }

    @Override
    public List<GroupParticipantsViewDto> getAllGroupsOfUser(Long userId) {
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }
        if(!userRepository.existsById(userId)){
            throw new NullPointerException("User does not exist");
        }
        User user =  userRepository.findById(userId).orElseThrow(()->new RuntimeException("The user is not registered in the application"));
        if (!groupParticipantsRepository.existsByMembersId(user)){
            throw new RuntimeException("The entered user does not have any group or belongs to any group");
        }
        List<GroupParticipants>groupParticipants = groupParticipantsRepository.findAllByMembersId(user);
        return groupParticipants.stream()
                .map(c -> new GroupParticipantsViewDto((groupRepository.findById(c.getGroupId())).get().getName(),c.getRole())).toList();
    }




}

