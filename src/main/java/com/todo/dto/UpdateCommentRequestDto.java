package com.todo.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    private final String contents;

    public UpdateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
