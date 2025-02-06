package com.todo.service;

import com.todo.dto.UserResponseDto;
import com.todo.entity.User;
import com.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(String username, String email, String password) {
        User user = new User(username, email, password);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findById(Long userId) {
        User user = userRepository.findByUserIdOrElseThrow(userId);
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }
}
