package com.jeneesh.splitmaster.Split.Master.restControllers;

import com.jeneesh.splitmaster.Split.Master.dto.ExpenseRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseResponseDto;
import com.jeneesh.splitmaster.Split.Master.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/{userId}/createSplit")
    public ExpenseResponseDto createSplit(@PathVariable Long userId, @RequestBody ExpenseRequestDto expenseRequestDto){
       return expenseService.createSplit(userId, expenseRequestDto);
    }
}