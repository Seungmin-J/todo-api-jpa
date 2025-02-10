package com.todo.controller;

import com.todo.dto.CommentRequestDto;
import com.todo.dto.CommentResponseDto;
import com.todo.dto.MemberResponseDto;
import com.todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long todoId,
            @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(name = "member")MemberResponseDto member) {

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다");
        }

        CommentResponseDto commentResponseDto = commentService.save(todoId, member.getMemberId(), requestDto.getContents());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);

    }
}
