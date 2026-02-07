package com.example.Assignment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subtopic {
    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private Topic topic;
}

