package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateContentsRequestDto {

    @NotBlank(message = "내용을 입력해주세요")
    private final String contents;

    public UpdateContentsRequestDto(String contents) {
        this.contents = contents;
    }
}
