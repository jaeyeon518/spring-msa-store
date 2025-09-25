package com.example.courseservice.service;

import com.example.courseservice.dto.CourseRequestDto;
import com.example.courseservice.dto.CourseUpdateRequestDto;
import com.example.courseservice.entity.Course;
import com.example.courseservice.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public Course create(CourseRequestDto requestDto) {
        Course course = Course.builder()
            .title(requestDto.getTitle())
            .description(requestDto.getDescription())
            .teacherId(requestDto.getTeacherId())
            .build();

        return courseRepository.save(course);
    }

    public Course findUser(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("course cannot find."));
    }

    @Transactional
    public Course update(Long id, CourseUpdateRequestDto requestDto) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("course cannot find."));

        course.update(requestDto.getTitle(), requestDto.getDescription(), requestDto.getTeacherId());

        return course;
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
