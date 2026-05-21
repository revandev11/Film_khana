package org.my.project.service;

import jakarta.transaction.Transactional;
import org.my.project.dto.request.RegisterRequest;
import org.my.project.dto.response.UserResponse;
import org.my.project.entity.User;
import org.my.project.enums.Roles;
import org.my.project.exception.UserNotFoundException;
import org.my.project.mapper.UserMapper;
import org.my.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found:"+ email));
    }
    @Transactional
    public UserResponse registrUser(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("user already exsist: "+request.getEmail());
        User user=new User();
        String encode = passwordEncoder.encode(request.getPassword());
        user.setPassword(encode);
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
         Roles roles=Roles.USER;
         user.setRoles(roles);
       return UserMapper.ToDto(userRepository.save(user)) ;
    }
}
