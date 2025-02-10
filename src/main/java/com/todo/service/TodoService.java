package com.todo.service;

import com.todo.dto.TodoResponseWithCommentsDto;
import com.todo.entity.Todo;
import com.todo.dto.TodoResponseDto;
import com.todo.entity.Member;
import com.todo.repository.TodoRepository;
import com.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public TodoResponseDto save(Long memberId, String title, String contents) {
        Todo todo = new Todo(title, contents);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"));
        todo.setMember(member);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(
                savedTodo.getTodoId(),
                savedTodo.getTitle(),
                savedTodo.getMember().getMemberName(),
                savedTodo.getContents());
    }

    public TodoResponseWithCommentsDto findById(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        TodoResponseWithCommentsDto todoDto = TodoResponseWithCommentsDto.toTodoDto(todo);
        return new TodoResponseWithCommentsDto(
                todoDto.getTodoId(),
                todoDto.getTitle(),
                todoDto.getMemberName(),
                todoDto.getContents(),
                todoDto.getComments());
    }

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponseDto::toTodoDto)
                .toList();
    }

    @Transactional
    public void updateContents(Long id, String contents) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        findTodo.updateContents(contents);
    }

    public void deleteTodo(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.delete(findTodo);
    }
}
