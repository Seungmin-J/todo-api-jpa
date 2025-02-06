package com.todo.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {

    private final Long userId;

    private final String title;

    private final String contents;

    public TodoRequestDto(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }
}
