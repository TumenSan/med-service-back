package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto.Response createUser(UserDto.Create userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new IllegalArgumentException("Login already exists");
        }

        User user = User.builder()
                .login(userDto.getLogin())
                .passwordHash(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole())
                .build();

        User savedUser = userRepository.save(user);
        return toResponseDto(savedUser);
    }

    @Transactional(readOnly = true)  // Исправлено: readOnly - это параметр, а не аннотация
    public UserDto.Response getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)  // Исправлено: readOnly - это параметр, а не аннотация
    public List<UserDto.Response> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)  // Исправлено: readOnly - это параметр, а не аннотация
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    private UserDto.Response toResponseDto(User user) {
        return UserDto.Response.builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
