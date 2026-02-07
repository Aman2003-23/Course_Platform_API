package com.example.Assignment.DTO.Search;

public record SearchMatchDto(
        String type,          // "topic" | "subtopic" | "content"
        String topicTitle,
        String subtopicId,
        String subtopicTitle,
        String snippet
) {}
