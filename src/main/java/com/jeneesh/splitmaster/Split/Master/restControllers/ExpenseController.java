package com.jeneesh.splitmaster.Split.Master.restControllers;

import com.jeneesh.splitmaster.Split.Master.dto.*;
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

    @PostMapping("/{userId}/createSplitManual")
    public ExpenseResponseDto createSplitManual(@PathVariable Long userId, @RequestBody ExpenseRequestDto expenseRequestDto){
       return expenseService.createSplitManual(userId, expenseRequestDto);
    }

    @PostMapping("/{userId}/createSplitAuto")
    public ExpenseResponseDto createSplitAuto(@PathVariable Long userId, @RequestBody ExpenseRequestDto expenseRequestDto){
        return expenseService.createSplitAuto(userId, expenseRequestDto);
    }

    @PostMapping("/{userId}/paySplit")
    public ExpensePaidResponseDto paySplit(@PathVariable Long userId, @RequestBody ExpensePayRequestDto expensePayRequestDto){
        return expenseService.paySplit(userId, expensePayRequestDto);
    }

    @GetMapping("/{userId}/viewAllSplits")
    public ExpenseOverallSplitsResDto viewAllSplits(@PathVariable Long userId,@RequestBody SplitsRequestDto splitsRequestDto){
        return expenseService.viewAllSplits(userId, splitsRequestDto);
    }

    @GetMapping("/{userId}/viewOweAndOwedAmount")
    public ExpensePaidResponseDto viewOweAndQwedAmount(@PathVariable Long userId, @RequestBody SplitsRequestDto splitsRequestDto){
        return expenseService.viewOweAndOwedAmount(userId, splitsRequestDto);
    }

}