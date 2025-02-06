package com.todo.dto;

import lombok.Getter;

@Getter
public class UpdateContentsRequestDto {

    private final String contents;

    public UpdateContentsRequestDto(String contents) {
        this.contents = contents;
    }
}
