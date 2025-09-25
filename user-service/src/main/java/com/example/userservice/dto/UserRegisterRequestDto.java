package com.example.userservice.dto;

import com.example.userservice.enums.UserRole;
import lombok.Getter;

@Getter
public class UserRegisterRequestDto {
    private String name;
    private String password;
    private String email;
    private String phone;
    private UserRole role; // Role can also be a simple String
}