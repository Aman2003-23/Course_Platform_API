package com.example.Assignment.Controller;

import com.example.Assignment.DTO.enrollment.EnrollmentResponseDto;
import com.example.Assignment.Service.EnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Enrollment")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

//    @PostMapping("/api/courses/{courseId}/enroll")
//    public EnrollmentResponseDto enroll(
//            @PathVariable String courseId) {
//
//        return enrollmentService.enroll(courseId);
//    }

    @PostMapping("/api/courses/{courseId}/enroll")
    public Map<String, Object> enroll(
            @PathVariable String courseId) {

        enrollmentService.enroll(courseId);

        return Map.of(
                "courseId", courseId,
                "message", "Enrolled successfully"
        );
    }
}
