package com.example.userservice.dto;

import com.example.userservice.enums.UserRole;
import com.example.userservice.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private UserRole role;

    // User 엔티티를 UserResponseDto로 변환하는 정적 메서드
    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }
}