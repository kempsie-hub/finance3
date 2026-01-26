package com.my.expense.controller;

import com.my.expense.dto.LoginDTO;
import com.my.expense.dto.RegisterDTO;
import com.my.expense.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private AuthService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(registrationService.registerUser(registerDTO));
    }

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(registrationService.loginUser(loginDTO));
    }
}
