package com.jeneesh.splitmaster.Split.Master.restControllers;

import com.jeneesh.splitmaster.Split.Master.dto.*;
import com.jeneesh.splitmaster.Split.Master.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/{userId}/create-group")
    public GroupResponseDto createGroup(@PathVariable Long userId, @RequestBody GroupRequestDto groupRequestDto){
        return groupService.createGroup(groupRequestDto, userId);
    }

    @DeleteMapping("/{userId}/delete-group/{groupId}")
    public GroupResponseDto deleteContact(@PathVariable Long userId, @PathVariable Long groupId){
        return groupService.deleteGroup(userId, groupId);
    }
    @PostMapping("/{userId}/add-contact-to-group/{groupId}")
    public GroupParticipantsDto addContactToGroup(@PathVariable Long userId,
                                                  @PathVariable Long groupId,
                                                  @RequestBody ContactRequestDto contactRequestDto){

        return groupService.addContactToGroup(userId, contactRequestDto, groupId);
    }

    @DeleteMapping("/{userId}/remove-contact/{groupId}")
    public GroupParticipantsDto removeContactFromGroup(@PathVariable Long userId, @PathVariable Long groupId,
                                                       @RequestBody ContactRequestDto contactRequestDto){

        return groupService.removeContactFromGroup(userId, contactRequestDto, groupId);

    }

    @GetMapping("/{userId}/get-all-groups-of-user")
    public List<GroupParticipantsViewDto> getAllGroupsOfUser(@PathVariable Long userId) {
        return groupService.getAllGroupsOfUser(userId);
    }
}
