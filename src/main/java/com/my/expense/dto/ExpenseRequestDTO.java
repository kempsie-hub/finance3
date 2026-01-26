package com.my.expense.dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseRequestDTO {

    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Expense Type Id is required")
    private Long expenseTypeId;

    @NotNull(message = "User Id is required")
    private Long userId;

    private Long subscriptionId;

    @NotNull(message = "Amount is required")
    private Double amount;
}
