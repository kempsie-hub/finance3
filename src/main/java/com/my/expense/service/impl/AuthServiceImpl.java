package com.my.expense.service.impl;

import com.my.expense.dto.LoginDTO;
import com.my.expense.entity.User;
import com.my.expense.exception.ExpenseTypeException;
import com.my.expense.repository.RoleRepository;
import com.my.expense.repository.UserRepository;
import com.my.expense.service.AuthService;
import com.my.expense.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String registerUser(RegisterDTO registerDTO) {
        Boolean userExists = userRepository.existsByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());
        if (userExists) {
            throw new ExpenseTypeException(HttpStatus.BAD_REQUEST, "Username or email already exists");
        }



        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName("USER")));
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }
}
