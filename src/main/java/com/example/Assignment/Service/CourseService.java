package com.example.Assignment.Service;

import com.example.Assignment.Entity.Course;

import java.util.List;

public interface CourseService {
    long countCourses();

    void saveAll(List<Course> courses);
}
