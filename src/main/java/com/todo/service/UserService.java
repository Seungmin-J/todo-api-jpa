package com.todo.service;

import com.todo.dto.UserResponseDto;
import com.todo.entity.User;
import com.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(String username, String email, String password) {
        User user = new User(username, email, password);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findByUserId(Long userId) {
        User user = userRepository.findByUserIdOrElseThrow(userId);
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toUserDto)
                .toList();
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String username, String email, String password) {
        User user = userRepository.findByUserIdOrElseThrow(id);
        if (user.getPassword().equals(password)) {
            user.setUsername(username);
            user.setEmail(email);
        }

        return new UserResponseDto(user.getUsername(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long id, String password) {
        User user = userRepository.findByUserIdOrElseThrow(id);
        userRepository.delete(user);
    }
}
