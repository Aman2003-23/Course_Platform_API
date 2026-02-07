package com.example.Assignment.Service;

import com.example.Assignment.DTO.Course.CourseDetailDto;
import com.example.Assignment.DTO.Course.CourseSummaryDto;
import com.example.Assignment.Entity.Course;
import com.example.Assignment.Mapper.CourseMapper;
import com.example.Assignment.Repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseBrowserServiceImpl implements CourseBrowserService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    @Override
    public List<CourseSummaryDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toSummary)
                .toList();
    }

    @Override
    public CourseDetailDto getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Course not found: " + id));

        return courseMapper.toDetail(course);
    }
}
