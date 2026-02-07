package com.example.Assignment.Entity;

import com.example.Assignment.Entity.auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubtopicProgress {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subtopic_id")
    private Subtopic subtopic;

    private boolean completed;

    private LocalDateTime completedAt;
}



