package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.ContactRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ContactResponseDto;
import com.jeneesh.splitmaster.Split.Master.dto.ContactViewResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.Contacts;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.ContactRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.ContactService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository,UserRepository userRepository){
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ContactResponseDto addContact(ContactRequestDto contactRequestDto, Long userId) {
        if (userId == null) {
            throw new RuntimeException("Invalid user id");
        }

        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User id does not exist"));
        UserValidation.validPhoneNumber(contactRequestDto.getPhoneNumber());
        if (!userRepository.existsByPhoneNumber(contactRequestDto.getPhoneNumber())) {
            throw new RuntimeException("The entered contact is not a user of this application");
        }

        User contactUser = userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber());

        if (user.getPhoneNumber().equals(contactUser.getPhoneNumber())) {
            throw new RuntimeException("Your phone number cannot be the same as your contact phone number");
        }
        if (contactRepository.existsByUserAndContact(user, contactUser)) {
            throw new RuntimeException("The entered contact already exists");
        }
        else {
            Contacts contacts = new Contacts(user, contactUser);
            contactRepository.save(contacts);
        }
            return new ContactResponseDto(contactUser.getUserName(), contactUser.getPhoneNumber(), user.getUserName(), user.getPhoneNumber());

    }

    @Override
    public ContactResponseDto deleteContact(ContactRequestDto  contactRequestDto, Long userId) {
        if(userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User id does not exist"));

        UserValidation.validPhoneNumber(contactRequestDto.getPhoneNumber());
        if (!userRepository.existsByPhoneNumber(contactRequestDto.getPhoneNumber())) {
            throw new RuntimeException("The entered contact is not a user of this application");
        }
        if(user.getPhoneNumber().equals(contactRequestDto.getPhoneNumber())){
            throw new RuntimeException("Your phone number cannot be the same as your contact phone number");
        }
         if(!contactRepository.existsByUserAndContact(user, userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber()))) {
             throw new RuntimeException("The entered contact is not in your contact list");
         }

         else{
             Contacts contacts = contactRepository.findByUserAndContact(user,
                     userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber()));
             contactRepository.delete(contacts);
         }
        User contactUser = userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber());


        return new ContactResponseDto(user.getUserName(), user.getPhoneNumber(),
                 contactUser.getUserName(), contactUser.getPhoneNumber());
    }

    @Override
    public List<ContactViewResponseDto> viewContacts(Long userId) {
        if(userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("User id does not exist");
        }
        List<Contacts>contacts = contactRepository.findByUser_UserId(userId);
        List<User> users = new ArrayList<>();
        for(Contacts contact:contacts){
            users.add(userRepository.findById(contact.getContactId()).orElseThrow(() -> new RuntimeException("User id does not exist")));
        }

        if(users.isEmpty()){
            throw  new RuntimeException("No contacts found in your contact list");
        }
        return users.stream()
                .map(c -> new ContactViewResponseDto(c.getUserName(),
                        c.getPhoneNumber())).toList();
    }

    @Override
    public ContactResponseDto viewContact(ContactRequestDto contactRequestDto,Long userId) {
        if(userId == null) {
            throw new RuntimeException("Invalid user id");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User id does not exist"));
        UserValidation.validPassword(contactRequestDto.getPhoneNumber());
        if(!userRepository.existsByPhoneNumber(contactRequestDto.getPhoneNumber())) {
            throw new RuntimeException("The entered contact does not exist");
        }
        User contactUser = userRepository.findByPhoneNumber(contactRequestDto.getPhoneNumber());
        if(!contactRepository.existsByUserAndContact(user, contactUser)) {
            throw new RuntimeException("The entered contact is not in your contact list");
        }

        return new ContactResponseDto(contactUser.getUserName(),
                contactUser.getPhoneNumber(), user.getUserName(), user.getPhoneNumber());
    }
}
