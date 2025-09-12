package com.example.userservice.dto;

import com.example.userservice.enums.UserRole;
import lombok.Getter;

@Getter
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String email;
    private UserRole role; // Role can also be a simple String
}