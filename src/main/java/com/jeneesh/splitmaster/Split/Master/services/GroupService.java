package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import java.util.List;

public interface GroupService {

    GroupResponseDto createGroup(GroupRequestDto groupRequestDto,Long userId);
    GroupResponseDto deleteGroup(Long userId, Long groupId);
    GroupParticipantsDto addContactToGroup(Long userId, ContactRequestDto contactRequestDto,Long groupId);
    GroupParticipantsDto removeContactFromGroup(Long userId, ContactRequestDto contactRequestDto, Long groupId);
    List<GroupParticipantsViewDto> getAllGroupsOfUser(Long userId);

}
