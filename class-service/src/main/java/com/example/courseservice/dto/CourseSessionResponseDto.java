package com.example.courseservice.dto;

import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.CourseSession;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CourseSessionResponseDto {

    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CourseSessionResponseDto fromEntity(CourseSession courseSession) {
        return CourseSessionResponseDto.builder()
            .id(courseSession.getId())
            .courseId(courseSession.getCourseId())
            .startDate(courseSession.getStartDate())
            .endDate(courseSession.getEndDate())
            .name(courseSession.getName())
            .createdAt(courseSession.getCreatedAt())
            .updatedAt(courseSession.getUpdatedAt())
            .build();
    }
}
