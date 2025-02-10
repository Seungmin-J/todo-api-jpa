package com.todo.dto;

import com.todo.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {

    private final Long id;

    private final String title;

    private final String memberName;

    private final String contents;

    public TodoResponseDto(Long id, String title, String memberName, String contents) {
        this.id = id;
        this.title = title;
        this.memberName = memberName;
        this.contents = contents;
    }

    public static TodoResponseDto toTodoDto(Todo todo) {
        return new TodoResponseDto(todo.getTodoId(), todo.getTitle(), todo.getMember().getMemberName(), todo.getContents());
    }
}
