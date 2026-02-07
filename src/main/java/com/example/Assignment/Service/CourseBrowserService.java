package com.example.Assignment.Service;

import com.example.Assignment.DTO.Course.CourseDetailDto;
import com.example.Assignment.DTO.Course.CourseSummaryDto;
import java.util.List;

public interface CourseBrowserService {

    List<CourseSummaryDto> getAllCourses();

    CourseDetailDto getCourseById(String id);
}
