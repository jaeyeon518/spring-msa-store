package com.example.courseservice.repository;

import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
}
