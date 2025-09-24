package com.example.userservice.controller;

import com.example.userservice.common.CommonResponseDto;
import com.example.userservice.dto.UserRegisterRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.dto.UserUpdateRequestDto;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final Environment env;

    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service"
            + ", port(local.server.port)=" + env.getProperty("local.server.port")
            + ", port(server.port)=" + env.getProperty("server.port")
            + ", gateway ip(env)=" + env.getProperty("gateway.ip")
            + ", rabbitmq host=" + env.getProperty("spring.application.rabbitmq.host")
            + ", rabbitmq port=" + env.getProperty("spring.application.rabbitmq.port")
            + ", rabbitmq username=" + env.getProperty("spring.application.rabbitmq.username")
            + ", rabbitmq password=" + env.getProperty("spring.application.rabbitmq.password")
            + ", token secret=" + env.getProperty("jwt.secret")
            + ", token expiration time=" + env.getProperty("jwt.expiration"));
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> registerUser(@RequestBody UserRegisterRequestDto requestDto) {
        User user = userService.registerUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponseDto<>(UserResponseDto.fromEntity(user))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> getUserById(@PathVariable Long id) {
        User user = userService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(UserResponseDto.fromEntity(user))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        User user = userService.updateUser(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(UserResponseDto.fromEntity(user))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CommonResponseDto<>());
    }
}
