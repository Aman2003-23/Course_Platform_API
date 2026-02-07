package com.example.Assignment.seed.dto;

import com.example.Assignment.Entity.Course;
import com.example.Assignment.Entity.Subtopic;
import com.example.Assignment.Entity.Topic;
import com.example.Assignment.Service.CourseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class SeedDataLoader implements ApplicationRunner {

    private final CourseService courseService;
    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (courseService.countCourses() > 0) {
            return;
        }

        ClassPathResource resource =
                new ClassPathResource("seed/courses.json");

        CourseSeedWrapperDto wrapper =
                objectMapper.readValue(resource.getInputStream(),
                        CourseSeedWrapperDto.class);

        List<CourseSeedDto> courses = wrapper.getCourses();


        List<Course> courseEntities = new ArrayList<>();

        for (CourseSeedDto courseDto : courses) {

            Course course = new Course();
            course.setId(courseDto.getId());
            course.setTitle(courseDto.getTitle());
            course.setDescription(courseDto.getDescription());

            List<Topic> topicEntities = new ArrayList<>();

            for (TopicSeedDto topicDto : courseDto.getTopics()) {

                Topic topic = new Topic();
                topic.setId(topicDto.getId());
                topic.setTitle(topicDto.getTitle());
                topic.setCourse(course); // VERY IMPORTANT

                List<Subtopic> subtopicEntities = new ArrayList<>();

                for (SubtopicSeedDto subtopicDto : topicDto.getSubtopics()) {

                    Subtopic subtopic = new Subtopic();
                    subtopic.setId(subtopicDto.getId());
                    subtopic.setTitle(subtopicDto.getTitle());
                    subtopic.setContent(subtopicDto.getContent());
                    subtopic.setTopic(topic); // VERY IMPORTANT

                    subtopicEntities.add(subtopic);
                }

                topic.setSubtopics(subtopicEntities);
                topicEntities.add(topic);
            }

            course.setTopics(topicEntities);
            courseEntities.add(course);
        }

        courseService.saveAll(courseEntities);

    }
}
