package com.ne.starter.services;

import com.ne.starter.dtos.requests.CreateUserDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.exceptions.ResourceNotFoundException;
import com.ne.starter.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(UUID id);
    public User createUser(CreateUserDto createUserDto , ERole roleEnum) ;
    public User updateUser(User user);
    public Boolean deleteUser(UUID id);
    public User getUserByEmail(String email) ;
    public User getLoggedInUser() throws ResourceNotFoundException;
    public boolean isNotUnique(User user) ;
}
