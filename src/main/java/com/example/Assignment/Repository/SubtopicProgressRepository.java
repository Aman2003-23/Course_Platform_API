package com.example.Assignment.Repository;

import com.example.Assignment.Entity.Enrollment;
import com.example.Assignment.Entity.Subtopic;
import com.example.Assignment.Entity.SubtopicProgress;
import com.example.Assignment.Entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubtopicProgressRepository
        extends JpaRepository<SubtopicProgress, Long> {



    List<SubtopicProgress> findByEnrollmentAndCompletedTrue(Enrollment enrollment);

    boolean existsByEnrollmentAndSubtopic(Enrollment enrollment,Subtopic subtopic);
}

