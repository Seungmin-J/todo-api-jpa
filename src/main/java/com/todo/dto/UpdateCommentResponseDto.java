package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponseDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private final String contents;

    private final String memberName;

    private final String todoTitle;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateCommentResponseDto(String contents, String memberName, String todoTitle, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.contents = contents;
        this.memberName = memberName;
        this.todoTitle = todoTitle;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
