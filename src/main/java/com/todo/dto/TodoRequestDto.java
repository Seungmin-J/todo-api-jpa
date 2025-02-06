package com.todo.entity;

import lombok.Getter;

@Getter
public class TodoRequestDto {

    private final String username;

    private final String title;

    private final String contents;

    public TodoRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
