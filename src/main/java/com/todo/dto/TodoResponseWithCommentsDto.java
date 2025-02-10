package com.todo.dto;

import com.todo.entity.Todo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoResponseWithCommentsDto {

    private final Long todoId;

    private final String title;

    private final String memberName;

    private final String contents;

    private final List<CommentResponseDto> comments;

    public TodoResponseWithCommentsDto(Long todoId, String title, String memberName, String contents, List<CommentResponseDto> comments) {
        this.todoId = todoId;
        this.title = title;
        this.memberName = memberName;
        this.contents = contents;
        this.comments = comments;
    }

    public static TodoResponseWithCommentsDto toTodoDto(Todo todo) {
        List<CommentResponseDto> comments = todo.getComments()
                .stream()
                .map(comment -> CommentResponseDto.toDto(comment))
                .collect(Collectors.toList());
        return new TodoResponseWithCommentsDto(todo.getTodoId(), todo.getTitle(), todo.getMember().getMemberName(), todo.getContents(), comments);
    }
}
