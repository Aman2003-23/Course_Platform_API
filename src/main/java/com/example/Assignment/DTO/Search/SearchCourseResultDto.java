package com.example.Assignment.DTO.Search;

import java.util.List;

public record SearchCourseResultDto(
        String courseId,
        String courseTitle,
        List<SearchMatchDto> matches
) {}
