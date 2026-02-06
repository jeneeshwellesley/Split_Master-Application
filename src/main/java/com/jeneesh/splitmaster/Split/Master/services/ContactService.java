package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.ContactRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ContactResponseDto;
import com.jeneesh.splitmaster.Split.Master.dto.ContactViewResponseDto;
import com.jeneesh.splitmaster.Split.Master.entities.Contacts;

import java.util.List;

public interface ContactService {
    ContactResponseDto addContact(ContactRequestDto contactRequestDto, Long userId);
    ContactResponseDto deleteContact(ContactRequestDto contactRequestDto, Long userId);
    List<ContactViewResponseDto> viewContacts(Long userId);
    ContactResponseDto viewContact(ContactRequestDto contactRequestDto ,Long userId);
}
