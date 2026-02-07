package com.example.Assignment.DTO.Course;

import java.time.LocalDateTime;

public record CompletedSubtopicDto(
        String subtopicId,
        String subtopicTitle,
        LocalDateTime completedAt
) {}

