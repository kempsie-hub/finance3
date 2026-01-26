package com.my.expense.exception.handler;

import com.my.expense.dto.ApiResponseDTO;
import com.my.expense.exception.ExceptionDetails;
import com.my.expense.exception.ExpenseTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(ExpenseTypeException.class)
    public ResponseEntity<ExceptionDetails> handleExpenseTypeException(ExpenseTypeException ex, WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return ResponseEntity.status(ex.getHttpStatus()).body(exceptionDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    ApiResponseDTO<Map<String, String>> response = new ApiResponseDTO<>(
            "Expense Application",
            "FAILURE",
            "Validation failed",
            errors
    );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}

}
