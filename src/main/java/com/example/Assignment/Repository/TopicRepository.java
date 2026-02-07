package com.example.Assignment.Repository;

import com.example.Assignment.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
}
