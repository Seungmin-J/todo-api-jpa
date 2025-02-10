package com.todo.service;

import com.todo.dto.CommentResponseDto;
import com.todo.entity.Comment;
import com.todo.entity.Member;
import com.todo.entity.Todo;
import com.todo.repository.CommentRepository;
import com.todo.repository.MemberRepository;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public CommentResponseDto save(Long todoId, Long memberId, String contents) {
        Todo todo = todoRepository.findByIdOrElseThrow(todoId);
        Member member = memberRepository.findByMemberIdOrElseThrow(memberId);

        Comment comment = new Comment(member, todo, contents);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
}
