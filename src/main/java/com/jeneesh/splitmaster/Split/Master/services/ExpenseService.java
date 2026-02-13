package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.ExpenseRequestDto;
import com.jeneesh.splitmaster.Split.Master.dto.ExpenseResponseDto;

public interface ExpenseService {
    ExpenseResponseDto createSplit(Long userId, ExpenseRequestDto expenseRequestDto);
}
