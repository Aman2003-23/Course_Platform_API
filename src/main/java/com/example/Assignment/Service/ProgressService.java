package com.example.Assignment.Service;

import com.example.Assignment.DTO.enrollment.EnrollmentProgressDto;

import java.util.List;
import java.util.Map;

public interface ProgressService {

    Map<String, Object> markCompleted(Long enrollmentId, String subtopicId);

    Map<String, Object> getCompletedSubtopics(Long enrollmentId);
}

