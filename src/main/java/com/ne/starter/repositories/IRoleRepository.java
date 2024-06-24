package com.ne.starter.repositories;

import com.ne.starter.enums.ERole;
import com.ne.starter.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(ERole roleName);
}
