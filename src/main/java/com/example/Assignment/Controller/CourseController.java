package com.example.Assignment.Controller;

import com.example.Assignment.DTO.Course.CourseDetailDto;
import com.example.Assignment.DTO.Course.CourseSummaryDto;
import com.example.Assignment.Service.CourseBrowserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name="Courses")

public class CourseController {
    private final CourseBrowserService courseBrowserService;

    @GetMapping
    public Map<String, List<CourseSummaryDto>> getAll() {

        return Map.of(
                "courses",
                courseBrowserService.getAllCourses()
        );
    }

    @GetMapping("/{id}")
    public CourseDetailDto getById(@PathVariable String id) {

        return courseBrowserService.getCourseById(id);
    }
}
