package com.ne.starter.services;

import com.ne.starter.dtos.requests.CreateRoleDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.models.Role;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    Role registerRole(CreateRoleDto dto);
    Role updateRole(UUID id, CreateRoleDto dto);
    Role getRole(UUID id);
    Role getRoleByName(ERole roleName);
    List<Role> getAllRoles();
    void deleteRole(UUID id);
    public boolean isRolePresent(ERole roleName);


}
