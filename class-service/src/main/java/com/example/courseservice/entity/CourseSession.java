package com.example.courseservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "course_sessions")
@NoArgsConstructor
public class CourseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private String name; // 예: "화요일 수업", "목요일 수업"
    private LocalDateTime startDate; // 수업 시작 시간
    private LocalDateTime endDate;  // 수업 종료 시간

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();


    @Builder
    public CourseSession(Long courseId, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.courseId = courseId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void update(Long courseId, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.courseId = courseId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}