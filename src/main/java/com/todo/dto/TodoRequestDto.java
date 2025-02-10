package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    private final Long memberId;

    @NotBlank(message = "제목을 입력해주세요")
    @Size(max = 20)
    private final String title;

    @NotBlank(message = "내용을 입력해주세요")
    private final String contents;

    public TodoRequestDto(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
