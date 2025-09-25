package com.example.courseservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "courses")
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Long teacherId; // teacher 정보는 userId로만 저장

    private Integer studentCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Course(String title, String description, Long teacherId, Integer studentCount) {
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.studentCount = studentCount;
    }

    public void update(String title, String description, Long teacherId) {
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
    }
}
