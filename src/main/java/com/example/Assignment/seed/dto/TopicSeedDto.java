package com.example.Assignment.seed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TopicSeedDto {
    String id;
    String title;
    List<SubtopicSeedDto> subtopics;
}
