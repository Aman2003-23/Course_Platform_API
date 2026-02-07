package com.example.Assignment.DTO.Search;

import java.util.List;

public record SearchResponseDto(
        String query,
        List<SearchCourseResultDto> results
) {}
