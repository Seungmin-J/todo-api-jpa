package com.todo.dto;

import com.todo.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponseDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private final String contents;

    private final String memberName;

    private final String todoTitle;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.memberName = comment.getMember().getMemberName();
        this.todoTitle = comment.getTodo().getTitle();
    }

    public CommentResponseDto(String contents, String memberName, String todoTitle) {
        this.contents = contents;
        this.memberName = memberName;
        this.todoTitle = todoTitle;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getContents(), comment.getMember().getMemberName(), comment.getTodo().getTitle());
    }
}
