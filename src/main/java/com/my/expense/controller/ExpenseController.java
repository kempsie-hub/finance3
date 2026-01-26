package com.my.expense.controller;

import com.my.expense.dto.ApiResponseDTO;
import com.my.expense.dto.ExpenseRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @PostMapping
    public ResponseEntity<ApiResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO expenseDTO) {
        return null;
    }

}