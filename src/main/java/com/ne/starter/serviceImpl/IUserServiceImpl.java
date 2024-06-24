package com.ne.starter.serviceImpl;

import com.ne.starter.dtos.requests.CreateUserDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.exceptions.BadRequestAlertException;
import com.ne.starter.exceptions.InternalServerErrorException;
import com.ne.starter.exceptions.NotFoundException;
import com.ne.starter.exceptions.ResourceNotFoundException;
import com.ne.starter.models.Role;
import com.ne.starter.models.User;
import com.ne.starter.repositories.IUserRepository;
import com.ne.starter.services.IRoleService;
import com.ne.starter.services.IUserService;
import com.ne.starter.utils.ExceptionUtils;
import com.ne.starter.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id " + id));
    }

    @Override
    public User createUser(CreateUserDto createUserDto, ERole roleEnum) {
        // user role
        try {
            Role role = roleService.getRoleByName(roleEnum);
            User user = new User();
            user.setFirstName(createUserDto.getFirstName());
            user.setLastName(createUserDto.getLastName());
            user.setEmail(createUserDto.getEmail());
            user.setPassword(HashUtil.hashPassword(createUserDto.getPassword()));
            validateNewRegistration(user);
            user.setRole(role);
            System.out.println("done=========");
            return this.userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());

        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userOptional = this.userRepository.findById(user.getId());
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User with id: " + user.getId().toString() + "is not found");
        }
        User userToUpdate = userOptional.get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        validateNewRegistration(user);
        return this.userRepository.save(userToUpdate);
    }

    @Override
    public Boolean deleteUser(UUID id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id.toString() + "is not found"));
        this.userRepository.delete(userToDelete);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        User userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + "is not found"));
        return userByEmail;

    }

    @Override
    public User getLoggedInUser() throws ResourceNotFoundException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser")
            throw new BadRequestAlertException("You are not logged in, try to log in");

        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s is not found", email)));
    }

    @Override
    public boolean isNotUnique(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();

    }

    public void validateNewRegistration(User user) {
        if (isNotUnique(user)) {
            throw new BadRequestAlertException("User with email " + user.getEmail() + " already exists");
        }

    }
}
