package com.example.courseservice.controller;

import com.example.courseservice.common.CommonResponseDto;
import com.example.courseservice.dto.CourseRequestDto;
import com.example.courseservice.dto.CourseResponseDto;
import com.example.courseservice.dto.CourseUpdateRequestDto;
import com.example.courseservice.entity.Course;
import com.example.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    private final Environment env;
    private final CourseService courseService;

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
    public ResponseEntity<CommonResponseDto<CourseResponseDto>> create(@RequestBody CourseRequestDto requestDto) {
        Course course = courseService.create(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponseDto<>(CourseResponseDto.fromEntity(course))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<CourseResponseDto>> findById(@PathVariable Long id) {
        Course course = courseService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(CourseResponseDto.fromEntity(course))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponseDto<CourseResponseDto>> updateUser(@PathVariable Long id, @RequestBody CourseUpdateRequestDto requestDto) {
        Course course = courseService.update(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponseDto<>(CourseResponseDto.fromEntity(course))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto<Void>> deleteUser(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CommonResponseDto<>());
    }
}
