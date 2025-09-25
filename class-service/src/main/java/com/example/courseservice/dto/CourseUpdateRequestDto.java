package com.example.courseservice.dto;

import lombok.Getter;

@Getter
public class CourseUpdateRequestDto {

    private Long id;
    private String title;
    private String description;
    private Long teacherId;
}
