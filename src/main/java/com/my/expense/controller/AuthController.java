package com.my.expense.controller;

import com.my.expense.dto.ApiResponseDTO;
import com.my.expense.dto.LoginDTO;
import com.my.expense.dto.RegisterDTO;
import com.my.expense.service.AuthService;
import com.my.expense.util.ResponseUtil;
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
    public ResponseEntity<ApiResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(
                ResponseUtil.success(
                        registrationService.loginUser(loginDTO),
                        LoginDTO.builder()
                                .usernameOrEmail(loginDTO.getUsernameOrEmail())
                                .build()
                        )
        );
    }
}
