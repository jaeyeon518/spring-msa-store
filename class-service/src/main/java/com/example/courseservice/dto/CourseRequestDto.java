package com.example.courseservice.dto;

import lombok.Getter;

@Getter
public class CourseRequestDto {

    private String title;
    private String description;
    private Long teacherId;
}
