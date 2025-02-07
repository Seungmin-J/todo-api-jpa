package com.todo.dto;

import lombok.Getter;

@Getter
public class DeleteRequestDto {

    private final String password;

    public DeleteRequestDto(String password) {
        this.password = password;
    }
}
