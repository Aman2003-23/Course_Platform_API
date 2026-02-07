package com.example.Assignment.DTO.enrollment;

import java.time.LocalDateTime;

public record EnrollmentResponseDto(
        Long enrollmentId,
        String courseId,
        String courseTitle,
        LocalDateTime enrolledAt
) {
}
