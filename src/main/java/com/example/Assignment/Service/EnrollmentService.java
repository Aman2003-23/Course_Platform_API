package com.example.Assignment.Service;

import com.example.Assignment.DTO.enrollment.EnrollmentResponseDto;

public interface EnrollmentService {

    EnrollmentResponseDto enroll(String courseId);
}
