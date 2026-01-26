package com.my.expense.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExpenseTypeException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

}
