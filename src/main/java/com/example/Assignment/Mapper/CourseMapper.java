package com.example.Assignment.Mapper;

import com.example.Assignment.DTO.Course.CourseDetailDto;
import com.example.Assignment.DTO.Course.CourseSummaryDto;
import com.example.Assignment.DTO.Course.SubtopicDto;
import com.example.Assignment.DTO.Course.TopicDto;
import com.example.Assignment.Entity.Course;
import com.example.Assignment.Entity.Subtopic;
import com.example.Assignment.Entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseSummaryDto toSummary(Course course) {

        int topicCount = course.getTopics().size();

        int subtopicCount =
                course.getTopics().stream()
                        .mapToInt(t -> t.getSubtopics().size())
                        .sum();

        return new CourseSummaryDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                topicCount,
                subtopicCount
        );
    }

    public CourseDetailDto toDetail(Course course) {

        return new CourseDetailDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTopics().stream()
                        .map(this::toTopic)
                        .toList()
        );
    }

    private TopicDto toTopic(Topic topic) {

        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getSubtopics().stream()
                        .map(this::toSubtopic)
                        .toList()
        );
    }

    private SubtopicDto toSubtopic(Subtopic subtopic) {

        return new SubtopicDto(
                subtopic.getId(),
                subtopic.getTitle(),
                subtopic.getContent()
        );
    }
}

