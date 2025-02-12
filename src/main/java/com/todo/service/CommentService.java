package com.todo.service;

import com.todo.dto.UpdateCommentResponseDto;
import com.todo.dto.CommentResponseDto;
import com.todo.entity.Comment;
import com.todo.entity.Member;
import com.todo.entity.Todo;
import com.todo.repository.CommentRepository;
import com.todo.repository.MemberRepository;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public CommentResponseDto findById(Long commentId) {
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        CommentResponseDto responseDto = CommentResponseDto.toDto(comment);
        return responseDto;
    }

    @Transactional
    public UpdateCommentResponseDto updateComment(Long commentId, Member sessionMember, String contents) {
        Comment comment = commentRepository.findCommentByCommentId(commentId);

        // 댓글의 memberId 와 session 의 memberId 비교
        if (!sessionMember.getMemberId().equals(comment.getMember().getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다");
        }
        comment.setContents(contents);

        return new UpdateCommentResponseDto(
                comment.getContents(),
                comment.getMember().getMemberName(),
                comment.getTodo().getTitle(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public void deleteComment(Long commentId, Member sessionMember) {
        Comment comment = commentRepository.findCommentByCommentId(commentId);

        // 댓글의 memberId 와 session의 memberId 비교
        if (!comment.getMember().getMemberId().equals(sessionMember.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다");
        }

        commentRepository.delete(comment);
    }
}
