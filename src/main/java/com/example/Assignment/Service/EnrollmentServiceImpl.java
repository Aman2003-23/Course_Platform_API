package com.example.Assignment.Service;

import com.example.Assignment.DTO.enrollment.EnrollmentResponseDto;
import com.example.Assignment.Entity.Course;
import com.example.Assignment.Entity.Enrollment;
import com.example.Assignment.Entity.auth.User;
import com.example.Assignment.Repository.CourseRepository;
import com.example.Assignment.Repository.EnrollmentRepository;
import com.example.Assignment.security.AuthenticatedUserProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Override
    @Transactional
    public EnrollmentResponseDto enroll(String courseId) {

        User user = authenticatedUserProvider.getCurrentUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (enrollmentRepository.existsByUserAndCourse(user, course)) {
            throw new RuntimeException("Already enrolled");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        return new EnrollmentResponseDto(
                saved.getId(),
                course.getId(),
                course.getTitle(),
                enrollment.getEnrolledAt()
        );

    }
}


