package com.jeneesh.splitmaster.Split.Master.services;

import com.jeneesh.splitmaster.Split.Master.dto.*;

public interface ExpenseService {
    ExpenseResponseDto createSplitManual(Long userId, ExpenseRequestDto expenseRequestDto);
    ExpenseAutoSplitResponse createSplitAuto(Long userId, ExpenseRequestDto expenseRequestDto);
    ExpensePaidResponseDto paySplit(Long userId, ExpensePayRequestDto expensePayRequestDto);
    ExpenseOverallSplitsResDto viewAllSplits(Long userId,SplitsRequestDto splitsRequestDto);
    ExpensePaidResponseDto viewOweAndOwedAmount(Long userId, SplitsRequestDto splitsRequestDto);

}
