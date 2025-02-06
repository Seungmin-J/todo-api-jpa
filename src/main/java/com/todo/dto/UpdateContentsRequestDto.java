package com.todo.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {

    private final String contents;

    public TodoUpdateRequestDto(String contents) {
        this.contents = contents;
    }
}
