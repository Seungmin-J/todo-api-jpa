package com.todo.dto;

import com.todo.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long userId;

    private final String username;

    private final String email;

    public UserResponseDto(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto toUserDto(User user) {
        return new UserResponseDto(user.getUserId(), user.getUsername(), user.getEmail());
    }
}
