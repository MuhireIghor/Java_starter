package com.ne.starter.repositories;

import com.ne.starter.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IClassRepository extends JpaRepository<Class, UUID> {
}
