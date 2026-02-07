package com.example.Assignment.DTO.Course;
import java.util.List;

public record TopicDto(
        String id,
        String title,
        List<SubtopicDto> subtopics
) {}
