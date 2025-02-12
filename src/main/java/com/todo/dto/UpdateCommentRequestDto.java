package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank(message = "내용을 입력하세요")
    private final String contents;

    public UpdateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
