package com.example.courseservice.service;

import com.example.courseservice.dto.CourseRequestDto;
import com.example.courseservice.dto.CourseSessionRequestDto;
import com.example.courseservice.dto.CourseSessionUpdateRequestDto;
import com.example.courseservice.dto.CourseUpdateRequestDto;
import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.CourseSession;
import com.example.courseservice.repository.CourseRepository;
import com.example.courseservice.repository.CourseSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseSessionService {

    private final CourseSessionRepository courseSessionRepository;

    @Transactional
    public CourseSession create(CourseSessionRequestDto requestDto) {
        CourseSession courseSession = CourseSession.builder()
            .courseId(requestDto.getCourseId())
            .name(requestDto.getName())
            .startDate(requestDto.getStartDate())
            .endDate(requestDto.getStartDate())
            .build();

        return courseSessionRepository.save(courseSession);
    }

    public CourseSession findUser(Long id) {
        return courseSessionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("course cannot find."));
    }

    @Transactional
    public CourseSession update(Long id, CourseSessionUpdateRequestDto requestDto) {
        CourseSession courseSession = courseSessionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("courseSession cannot find."));

        courseSession.update(requestDto.getCourseId(), requestDto.getName(), requestDto.getStartDate(), requestDto.getEndDate());

        return courseSession;
    }

    public void delete(Long id) {
        courseSessionRepository.deleteById(id);
    }
}
