package com.todo.dto;

import com.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {

    private final Long todoId;

    private final String title;

    private final String memberName;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public TodoResponseDto(Long todoId, String title, String memberName, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.todoId = todoId;
        this.title = title;
        this.memberName = memberName;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TodoResponseDto toTodoDto(Todo todo) {
        return new TodoResponseDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getMember().getMemberName(),
                todo.getContents(),
                todo.getCreatedAt(),
                todo.getModifiedAt());
    }
}
