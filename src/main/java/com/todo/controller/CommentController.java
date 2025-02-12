package com.todo.controller;

import com.todo.dto.*;
import com.todo.entity.Member;
import com.todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long todoId,
            @Validated @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(name = "member") Member member) {

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다");
        }

        CommentResponseDto commentResponseDto = commentService.save(todoId, member.getMemberId(), requestDto.getContents());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);

    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long commentId) {
        CommentResponseDto responseDto = commentService.findById(commentId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponseDto> update(
            @PathVariable Long commentId,
            @SessionAttribute(name = "member") Member member,
            @Validated @RequestBody UpdateCommentRequestDto requestDto) {
        UpdateCommentResponseDto updateCommentResponseDto = commentService.updateComment(commentId, member, requestDto.getContents());
        return new ResponseEntity<>(updateCommentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId,
                                       @SessionAttribute("member") Member member) {
        commentService.deleteComment(commentId, member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
