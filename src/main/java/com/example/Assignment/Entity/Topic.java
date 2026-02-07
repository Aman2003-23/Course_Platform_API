package com.example.Assignment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Topic {

        @Id
        private String id;
        private String title;
        @ManyToOne
        private Course course;
        @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Subtopic> subtopics;


}
