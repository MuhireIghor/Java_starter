package com.ne.starter.repositories;

import com.ne.starter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

}
