package com.jeneesh.splitmaster.Split.Master.serviceImpls;

import com.jeneesh.splitmaster.Split.Master.dto.ContactResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.Contacts;
import com.jeneesh.splitmaster.Split.Master.entities.User;
import com.jeneesh.splitmaster.Split.Master.repositories.ContactRepository;
import com.jeneesh.splitmaster.Split.Master.repositories.UserRepository;
import com.jeneesh.splitmaster.Split.Master.services.ContactService;
import com.jeneesh.splitmaster.Split.Master.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ContactResponseDto addContact(String phoneNumber,Long userId) {
        if(userId == null){
            throw new RuntimeException("Invalid user id");
        }

        User user = userRepository.findById(userId).
                orElseThrow(() ->new RuntimeException("User id does not exist"));
        UserValidation.validPhoneNumber(phoneNumber);
        if(!userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new RuntimeException("The entered contact is not a user of this application");
        }

        User contactUser = userRepository.findByPhoneNumber(phoneNumber);

        if(user.getPhoneNumber().equals(contactUser.getPhoneNumber())){
            throw new RuntimeException("Your phone number cannot be the same as your contact phone number");
        }
        Contacts contacts = new Contacts(user,contactUser);
        contactRepository.save(contacts);
        return new ContactResponseDto(contactUser.getUserName(),contactUser.getPhoneNumber());
    }

    @Override
    public String deleteContact(String phoneNumber) {
        return "";
    }

    @Override
    public List<Contacts> viewContacts(Long userId) {
        return List.of();
    }

    @Override
    public ContactResponseDto viewContact(String phoneNumber) {
        return null;
    }
}
