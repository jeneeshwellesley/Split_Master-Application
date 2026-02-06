package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.ContactRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupParticipantsDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.GroupResponseDto;

public interface GroupService {

    GroupResponseDto createGroup(GroupRequestDto groupRequestDto,Long userId);
    GroupResponseDto deleteGroup(Long userId, Long groupId);
    GroupParticipantsDto addContactToGroup(Long userId, ContactRequestDto contactRequestDto,Long groupId);

}
