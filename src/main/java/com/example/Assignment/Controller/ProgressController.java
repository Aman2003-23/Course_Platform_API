package com.example.Assignment.Controller;

import com.example.Assignment.DTO.enrollment.EnrollmentProgressDto;
import com.example.Assignment.Service.ProgressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "Progress")
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/{enrollmentId}/subtopics/{subtopicId}/complete")
    public Map<String, Object> completeSubtopic(
            @PathVariable Long enrollmentId,
            @PathVariable String subtopicId) {

        return progressService.markCompleted(enrollmentId, subtopicId);
    }

    @GetMapping("/{id}/progress")
    //here id is enrollmentId
    public Map<String, Object> progress(@PathVariable Long id) {

        return progressService.getCompletedSubtopics(id);
    }
}


