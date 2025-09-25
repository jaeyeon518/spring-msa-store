package com.example.userclassservice.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_class",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "class_id"}))
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;  // user-service의 User ID

    @Column(name = "class_id", nullable = false)
    private Long classId; // class-service의 Class ID

    private LocalDateTime joinedAt = LocalDateTime.now();
    private Integer attendanceCount = 0;
    private LocalDateTime lastAttendance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

}
