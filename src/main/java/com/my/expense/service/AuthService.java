package com.my.expense.service;

import com.my.expense.dto.LoginDTO;
import com.my.expense.dto.RegisterDTO;

public interface AuthService {

    String registerUser(RegisterDTO registerDTO);

    String loginUser(LoginDTO loginDTO);
}
