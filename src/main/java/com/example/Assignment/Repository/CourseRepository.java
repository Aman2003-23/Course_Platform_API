package com.example.Assignment.Repository;

import com.example.Assignment.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("""
        SELECT DISTINCT c FROM Course c
        JOIN c.topics t
        JOIN t.subtopics s
        WHERE
        LOWER(c.title) LIKE LOWER(CONCAT('%', :q, '%'))
        OR LOWER(c.description) LIKE LOWER(CONCAT('%', :q, '%'))
        OR LOWER(t.title) LIKE LOWER(CONCAT('%', :q, '%'))
        OR LOWER(s.title) LIKE LOWER(CONCAT('%', :q, '%'))
        OR LOWER(s.content) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    List<Course> searchCourses(@Param("q") String q);

}
