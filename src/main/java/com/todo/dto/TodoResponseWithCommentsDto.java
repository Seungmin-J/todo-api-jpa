package com.todo.dto;

import com.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoResponseWithCommentsDto {

    private final Long todoId;

    private final String title;

    private final String memberName;

    private final String contents;

    private final List<CommentResponseDto> comments;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public TodoResponseWithCommentsDto(Long todoId, String title, String memberName, String contents, List<CommentResponseDto> comments, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.todoId = todoId;
        this.title = title;
        this.memberName = memberName;
        this.contents = contents;
        this.comments = comments;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TodoResponseWithCommentsDto toTodoDto(Todo todo) {
        List<CommentResponseDto> comments = todo.getComments()
                .stream()
                .map(comment -> CommentResponseDto.toDto(comment))
                .collect(Collectors.toList());
        return new TodoResponseWithCommentsDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getMember().getMemberName(),
                todo.getContents(),
                comments,
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }
}
