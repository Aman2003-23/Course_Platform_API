package com.example.Assignment.Service;

import com.example.Assignment.Entity.Course;
import com.example.Assignment.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public long countCourses() {
         return courseRepository.count();
    }

    @Override
    public void saveAll(List<Course> courses) {
          courseRepository.saveAll(courses);
    }
}
