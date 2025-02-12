package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateTodoContentsRequestDto {

    @NotBlank(message = "내용을 입력해주세요")
    private final String contents;

    public UpdateTodoContentsRequestDto(String contents) {
        this.contents = contents;
    }
}
