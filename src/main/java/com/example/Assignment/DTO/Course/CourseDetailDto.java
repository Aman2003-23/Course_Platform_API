package com.example.Assignment.DTO.Course;

import java.util.List;

public record CourseDetailDto(
        String id,
        String title,
        String description,
        List<TopicDto> topics
) {}

