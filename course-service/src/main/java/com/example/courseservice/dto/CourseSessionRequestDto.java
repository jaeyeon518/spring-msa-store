package com.example.courseservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CourseSessionRequestDto {

    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
}
