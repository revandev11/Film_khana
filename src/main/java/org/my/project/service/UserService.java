package org.my.project.service;

import jakarta.transaction.Transactional;
import org.my.project.dto.request.RegisterRequest;
import org.my.project.dto.request.UserCreateRequest;
import org.my.project.dto.request.UserUpdateRequest;
import org.my.project.dto.response.UserResponse;
import org.my.project.entity.User;
import org.my.project.enums.Roles;
import org.my.project.exception.ResourceNotFoundException;
import org.my.project.exception.UserAlreadyExxsist;
import org.my.project.exception.UserNotFoundException;
import org.my.project.mapper.UserMapper;
import org.my.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userrepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userrepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public UserResponse createUser(UserCreateRequest request){
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExxsist("user already exsist");

        User user=new User();
        String encode = passwordEncoder.encode(request.getPassword());
        user.setPassword(encode);
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        Roles roles=Roles.USER;
       user.setRoles(roles);
        User save=userRepository.save(user);
        return UserMapper.ToDto(save);
    }

    public List<UserResponse>getAllUSers(){
        List<User>users=userRepository.findAll();
     return users.stream()
                .map(UserMapper::ToDto)
                .toList();
    }
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id:" + id));
        return new UserResponse(user.getId(),user.getUserName(),user.getEmail());
    }
    @Transactional
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new UserAlreadyExxsist("user is already exsists");
        }
        userRepository.deleteById(id);
    }
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equalsIgnoreCase(request.getEmail())) {
            boolean emailExists = userRepository.existsByEmail(request.getEmail());
            if (emailExists) {
                throw new UserAlreadyExxsist("User with this email already exists: " + request.getEmail());
            }
        }
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        User updatedUser = userRepository.save(user);
        return new UserResponse(updatedUser.getId(), updatedUser.getUserName(), updatedUser.getEmail());
    }

}
