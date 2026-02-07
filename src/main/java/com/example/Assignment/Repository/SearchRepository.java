package com.example.Assignment.Repository;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
    private final EntityManager entityManager;

    public List<Object[]> searchRaw(String q) {
        String sql = """
            SELECT 
              c.id AS course_id,
              c.title AS course_title,

              t.title AS topic_title,

              s.id AS subtopic_id,
              s.title AS subtopic_title,
              s.content AS content

            FROM course c
            JOIN topic t ON t.course_id = c.id
            JOIN subtopic s ON s.topic_id = t.id

            WHERE
                LOWER(c.title) LIKE LOWER(CONCAT('%', :q, '%'))
             OR LOWER(c.description) LIKE LOWER(CONCAT('%', :q, '%'))
             OR LOWER(t.title) LIKE LOWER(CONCAT('%', :q, '%'))
             OR LOWER(s.title) LIKE LOWER(CONCAT('%', :q, '%'))
             OR LOWER(s.content) LIKE LOWER(CONCAT('%', :q, '%'))
        """;

        return entityManager
                .createNativeQuery(sql)
                .setParameter("q", q)
                .getResultList();
    }

}
