package com.example.Assignment.DTO.Course;

public record CourseSummaryDto(
        String id,
        String title,
        String description,
        int topicCount,
        int subtopicCount
) {}
