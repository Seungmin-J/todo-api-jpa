package com.todo.service;

import com.todo.dto.TodoResponseWithCommentsDto;
import com.todo.entity.Todo;
import com.todo.dto.TodoResponseDto;
import com.todo.entity.Member;
import com.todo.repository.TodoRepository;
import com.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoResponseDto save(Long memberId, String title, String contents) {
        Todo todo = new Todo(title, contents);
        Member member = memberRepository.findByMemberIdOrElseThrow(memberId);
        todo.setMember(member);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(
                savedTodo.getTodoId(),
                savedTodo.getTitle(),
                savedTodo.getMember().getMemberName(),
                savedTodo.getContents(), todo.getCreatedAt(), todo.getModifiedAt());
    }

    // 일정 조회 시 일정에 달린 댓글도 함께 리턴
    public TodoResponseWithCommentsDto findById(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        TodoResponseWithCommentsDto todoDto = TodoResponseWithCommentsDto.toTodoDto(todo);
        return new TodoResponseWithCommentsDto(
                todoDto.getTodoId(),
                todoDto.getTitle(),
                todoDto.getMemberName(),
                todoDto.getContents(),
                todoDto.getComments(),
                todoDto.getCreatedAt(),
                todoDto.getModifiedAt());
    }

    // Pageable 을 사용해 페이지와 페이지 크기 전달받아 페이징
    public Page<TodoResponseDto> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable)
                .map(TodoResponseDto::toTodoDto);
    }

    // 세션에서 Member를 받아와 해당 일정의 작성자와 일치하는지 검증 후 update
    @Transactional
    public void updateContents(Long id, String contents, Member member) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!member.getMemberId().equals(findTodo.getMember().getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다");
        }

        findTodo.updateContents(contents);
    }

    public void deleteTodo(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.delete(findTodo);
    }
}
