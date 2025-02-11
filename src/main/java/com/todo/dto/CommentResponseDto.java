package com.todo.dto;

import com.todo.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private final String contents;

    private final String memberName;

    private final String todoTitle;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;


    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.memberName = comment.getMember().getMemberName();
        this.todoTitle = comment.getTodo().getTitle();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public CommentResponseDto(String contents, String memberName, String todoTitle, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.contents = contents;
        this.memberName = memberName;
        this.todoTitle = todoTitle;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getContents(),
                comment.getMember().getMemberName(),
                comment.getTodo().getTitle(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
