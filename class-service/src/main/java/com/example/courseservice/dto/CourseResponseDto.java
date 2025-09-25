package com.example.courseservice.dto;

import com.example.courseservice.entity.Course;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CourseResponseDto {

    private Long id;
    private String title;
    private String description;
    private Long teacherId;
    private Integer studentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CourseResponseDto fromEntity(Course course) {
        return CourseResponseDto.builder()
            .id(course.getId())
            .title(course.getTitle())
            .description(course.getTitle())
            .teacherId(course.getTeacherId())
            .studentCount(course.getStudentCount())
            .createdAt(course.getCreatedAt())
            .updatedAt(course.getUpdatedAt())
            .build();
    }
}
