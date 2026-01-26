package com.my.expense.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    public static void main(String[] args) {
        System.out.println(encrypt("profile"));
    }
    public static String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
