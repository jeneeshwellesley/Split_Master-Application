package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.ContactRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupParticipantsDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.GroupParticipants;
import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.ContactRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.GroupParticipantsRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.GroupRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.GroupService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public GroupResponseDto deleteGroup(Long userId, Long groupId) {
        if(userId==null){
            throw new NullPointerException("userId is null");
        }
        if(!userRepository.existsById(userId)){
            throw new NullPointerException("User does not exist");
        }
        if(groupId==null){
            throw new NullPointerException("groupId is null");
        }
        Groups group = groupRepository.findById(groupId).orElseThrow(() -> new  RuntimeException("Group does not exist"));
        groupRepository.delete(group);

        return new GroupResponseDto(group.getName(), group.getCreatedBy());
    }

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

        if(groupParticipantsRepository.existsByMembersId(contactUser)){
            throw new RuntimeException("The entered contact already exists in the group");
        }


        GroupParticipants groupParticipants = new GroupParticipants(group,contactUser,"MEMBER");
        groupParticipantsRepository.save(groupParticipants);
        return new GroupParticipantsDto(group.getGroupId(),group.getName(),
                contactUser.getUserId(),contactUser.getUserName(),"MEMBER");

    }


}

