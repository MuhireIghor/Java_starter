package com.ne.starter.repositories;

import com.ne.starter.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface ICourseRepository extends JpaRepository<Course, UUID> {
}
