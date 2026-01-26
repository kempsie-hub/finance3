package com.my.expense.controller;

import com.my.expense.entity.ExpenseType;
import com.my.expense.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/expense-types")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    @Autowired
    public ExpenseTypeController(ExpenseTypeService expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeService.findAll();
    }


    @CrossOrigin("*")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ExpenseType> getExpenseTypeById(@PathVariable Long id) {
        Optional<ExpenseType> expenseType = expenseTypeService.findById(id);
        return expenseType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ExpenseType createExpenseType(@RequestBody ExpenseType expenseType) {
        return expenseTypeService.save(expenseType);
    }

    @CrossOrigin("*")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ExpenseType> updateExpenseType(@PathVariable Long id, @RequestBody ExpenseType expenseTypeDetails) {
        Optional<ExpenseType> expenseType = expenseTypeService.findById(id);
        if (expenseType.isPresent()) {
            ExpenseType updatedExpenseType = expenseType.get();
            updatedExpenseType.setName(expenseTypeDetails.getName());
            updatedExpenseType.setDescription(expenseTypeDetails.getDescription());
            updatedExpenseType.setRegistrationRequired(expenseTypeDetails.isRegistrationRequired());
            updatedExpenseType.setStatus(expenseTypeDetails.getStatus());
            updatedExpenseType.setCreatedBy(expenseTypeDetails.getCreatedBy());
            updatedExpenseType.setUpdatedBy(expenseTypeDetails.getUpdatedBy());
            updatedExpenseType.setCreatedDate(expenseTypeDetails.getCreatedDate());
            updatedExpenseType.setUpdatedDate(expenseTypeDetails.getUpdatedDate());
            return ResponseEntity.ok(expenseTypeService.save(updatedExpenseType));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin("*")
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteExpenseType(@PathVariable Long id) {
        if (expenseTypeService.findById(id).isPresent()) {
            expenseTypeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}