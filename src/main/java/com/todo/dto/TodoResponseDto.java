package com.todo.entity;

import lombok.Getter;

@Getter
public class TodoResponseDto {

    private final Long id;

    private final String title;

    private final String username;

    private final String contents;

    public TodoResponseDto(Long id, String title, String username, String contents) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.contents = contents;
    }

    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getUsername(), todo.getContents());
    }
}
