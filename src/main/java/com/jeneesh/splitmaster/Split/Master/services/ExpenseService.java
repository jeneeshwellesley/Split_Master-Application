package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.ExpensePaidResponseDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpensePayRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseResponseDto;

public interface ExpenseService {
    ExpenseResponseDto createSplitManual(Long userId, ExpenseRequestDto expenseRequestDto);
    ExpenseResponseDto createSplitAuto(Long userId, ExpenseRequestDto expenseRequestDto);
    ExpensePaidResponseDto paySplit(Long userId, ExpensePayRequestDto expensePayRequestDto);
}
