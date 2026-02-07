package com.example.Assignment.Service;

import com.example.Assignment.DTO.Course.CompletedSubtopicDto;
import com.example.Assignment.DTO.enrollment.EnrollmentProgressDto;
import com.example.Assignment.Entity.Enrollment;
import com.example.Assignment.Entity.Subtopic;
import com.example.Assignment.Entity.SubtopicProgress;
import com.example.Assignment.Entity.auth.User;
import com.example.Assignment.Repository.EnrollmentRepository;
import com.example.Assignment.Repository.SubtopicProgressRepository;
import com.example.Assignment.Repository.SubtopicRepository;
import com.example.Assignment.security.AuthenticatedUserProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final EnrollmentRepository enrollmentRepository;
    private final SubtopicRepository subtopicRepository;
    private final SubtopicProgressRepository progressRepository;

    @Override
    public Map<String, Object> markCompleted(Long enrollmentId, String subtopicId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new RuntimeException("Subtopic not found"));

        if (progressRepository.existsByEnrollmentAndSubtopic(enrollment, subtopic)) {
            return Map.of("message", "Already completed");
        }

        SubtopicProgress progress = new SubtopicProgress();
        progress.setEnrollment(enrollment);
        progress.setSubtopic(subtopic);
        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        progressRepository.save(progress);

        return Map.of(
                "message", "Subtopic marked completed",
                "subtopicId", subtopic.getId(),
                "enrollmentId", enrollment.getId()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getCompletedSubtopics(Long enrollmentId) {

        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Enrollment not found"));

        List<SubtopicProgress> completed =
                progressRepository.findByEnrollmentAndCompletedTrue(enrollment);

        List<Map<String, Object>> items =
                completed.stream()
                        .map(p -> {

                            Map<String, Object> m = new HashMap<>();
                            m.put("subtopicId", p.getSubtopic().getId());
                            m.put("title", p.getSubtopic().getTitle());
                            m.put("completedAt",
                                    p.getCompletedAt()==null
                            ?null
                                    : p.getCompletedAt().toString());

                            return m;
                        })
                        .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("enrollmentId", enrollmentId);
        result.put("completedItems", items);

        return result;

    }
}

