package com.todo.service;

import com.todo.entity.Todo;
import com.todo.dto.TodoResponseDto;
import com.todo.entity.User;
import com.todo.repository.TodoRepository;
import com.todo.repository.UserRepository;
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
    private final UserRepository userRepository;

    public TodoResponseDto save(Long userId, String title, String contents) {
        Todo todo = new Todo(title, contents);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"));
        todo.setUser(user);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getUser().getUsername(),
                savedTodo.getContents());
    }

    public TodoResponseDto findById(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getUser().getUsername(),
                todo.getContents());
    }

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponseDto::toTOdoDto)
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
