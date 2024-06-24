package com.ne.starter.serviceImpl;

import com.ne.starter.dtos.requests.CreateBookDto;
import com.ne.starter.dtos.requests.CreateRoleDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.exceptions.InternalServerErrorException;
import com.ne.starter.exceptions.NotFoundException;
import com.ne.starter.models.Role;
import com.ne.starter.repositories.IRoleRepository;
import com.ne.starter.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role registerRole(CreateRoleDto dto) {
        Role role = new Role();
        role.setRoleName(dto.getRole());
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID id, CreateRoleDto dto) {
        Role oldRole = getRole(id);
        oldRole.setRoleName(dto.getRole());
        return roleRepository.save(oldRole);
    }

    @Override
    public Role getRole(UUID id) {
        return roleRepository.findById(id).orElseThrow(()->new NotFoundException("Role not found with id: "+id));
    }

    @Override
    public Role getRoleByName(ERole roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(()->new NotFoundException("Role not found with name: "+roleName));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public void deleteRole(UUID id) {
        Role role = getRole(id);
        roleRepository.delete(role);



    }
    @Override
    public boolean isRolePresent(ERole roleName) {
        try {
            return roleRepository.findByRoleName(roleName).isPresent();
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}

