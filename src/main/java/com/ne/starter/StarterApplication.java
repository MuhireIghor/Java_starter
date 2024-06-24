package com.ne.starter;

import com.ne.starter.dtos.requests.CreateRoleDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.models.Role;
import com.ne.starter.serviceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class StarterApplication {
	private RoleServiceImpl roleService;
	@Autowired
	public StarterApplication(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
	}

	@Bean
	Set<ERole> registerRoles(){
		Set<ERole> userRoleSet = new HashSet<>();
		userRoleSet.add(ERole.ADMIN);
		userRoleSet.add(ERole.STUDENT);
		userRoleSet.add(ERole.TEACHER);
		for (ERole role : userRoleSet){
			if(!(roleService.isRolePresent(role))){
				CreateRoleDto dto = new CreateRoleDto(
						role
				);
				 roleService.registerRole(dto);
			}
		}

        return userRoleSet;
    }

}
