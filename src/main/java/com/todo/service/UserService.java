package com.todo.service;

import com.todo.dto.LoginRequestDto;
import com.todo.dto.UserResponseDto;
import com.todo.entity.User;
import com.todo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(String username, String email, String password) {
        User user = new User(username, email, password);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findByUserId(Long userId) {
        User user = userRepository.findByUserIdOrElseThrow(userId);
        return new UserResponseDto(user.getUserId(), user.getUsername(), user.getEmail());
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

        return new UserResponseDto(user.getUserId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long id, String password) {
        User user = userRepository.findByUserIdOrElseThrow(id);
        userRepository.delete(user);
    }

    public UserResponseDto validateUser(LoginRequestDto requestDto, HttpSession session) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "일치하는 Email이 없습니다"));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        UserResponseDto userResponseDto = new UserResponseDto(user.getUserId(), user.getUsername(), user.getEmail());
        session.setAttribute("user", userResponseDto);

        session.setAttribute("sessionKey", user.getUserId());
        return userResponseDto;
    }
}
