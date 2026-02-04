package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.ContactResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.Contacts;

import java.util.List;

public interface ContactService {
    ContactResponseDto addContact(String phoneNumber, Long userId);
    String deleteContact(String phoneNumber);
    List<Contacts> viewContacts(Long userId);
    ContactResponseDto viewContact(String phoneNumber);
}
