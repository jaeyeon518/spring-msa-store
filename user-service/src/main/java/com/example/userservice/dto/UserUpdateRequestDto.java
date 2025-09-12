package com.example.userservice.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String username;
    private String password; // Optional password update
    private String email;
}