package com.example.courseservice.controller;

import com.example.courseservice.common.CommonResponseDto;
import com.example.courseservice.dto.*;
import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.CourseSession;
import com.example.courseservice.service.CourseSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
@Slf4j
public class CourseSessionController {

    private final Environment env;
    private final CourseSessionService courseSessionService;

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
        );
    }

    @PostMapping
    public ResponseEntity<CommonResponseDto<CourseSessionResponseDto>> create(@RequestBody CourseSessionRequestDto requestDto) {
        CourseSession courseSession = courseSessionService.create(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponseDto<>(CourseSessionResponseDto.fromEntity(courseSession))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<CourseSessionResponseDto>> findById(@PathVariable Long id) {
        CourseSession courseSession = courseSessionService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(CourseSessionResponseDto.fromEntity(courseSession))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponseDto<CourseSessionResponseDto>> updateUser(@PathVariable Long id, @RequestBody CourseSessionUpdateRequestDto requestDto) {
        CourseSession courseSession = courseSessionService.update(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(CourseSessionResponseDto.fromEntity(courseSession))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto<Void>> deleteUser(@PathVariable Long id) {
        courseSessionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CommonResponseDto<>());
    }
}
