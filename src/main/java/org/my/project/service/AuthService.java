package org.my.project.service;

import jakarta.transaction.Transactional;
import org.my.project.dto.request.LoginRequest;
import org.my.project.dto.request.RegisterRequest;
import org.my.project.dto.response.AuthResponse;
import org.my.project.dto.response.UserResponse;
import org.my.project.entity.User;
import org.my.project.enums.Roles;
import org.my.project.exception.UserAlreadyExxsist;
import org.my.project.exception.UserNotFoundException;
import org.my.project.mapper.UserMapper;
import org.my.project.repository.UserRepository;
import org.my.project.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found:"+ email));
    }
    @Transactional
    public AuthResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExxsist("User already exists with email: " + request.getEmail());
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Roles.USER);

        User savedUser = userRepository.save(user);
        String token = jwtTokenProvider.generateToken(
                savedUser.getEmail(),
                savedUser.getRoles().name()
        );
        UserResponse userResponse = UserMapper.ToDto(savedUser);

        return new AuthResponse(token, userResponse);
    }
    @Transactional
    public AuthResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email is not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Password is incorrect");
        }
        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                user.getRoles().name()
        );
        UserResponse userResponse = UserMapper.ToDto(user);
        return new AuthResponse(token, userResponse);


    }
}
