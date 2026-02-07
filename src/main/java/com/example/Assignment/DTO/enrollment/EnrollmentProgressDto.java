package com.example.Assignment.DTO.enrollment;

import com.example.Assignment.DTO.Course.CompletedSubtopicDto;

import java.util.List;

public record EnrollmentProgressDto(
        Long enrollmentId,
        String courseId,
        String courseTitle,
        int totalSubtopics,
        int completedSubtopics,
        double completionPercentage,
        List<CompletedSubtopicDto> completedItems
) {}
