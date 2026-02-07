package com.example.Assignment.Repository;

import com.example.Assignment.Entity.Course;
import com.example.Assignment.Entity.Enrollment;
import com.example.Assignment.Entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByUserAndCourse(User user, Course course);

    Optional<Enrollment> findByUserAndCourse(User user, Course course);

    Optional<Enrollment> findByUserAndCourse_Id(
            User user,
            String courseId
    );

}
