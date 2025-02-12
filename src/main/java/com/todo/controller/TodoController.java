package com.todo.controller;

import com.todo.dto.TodoRequestDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoResponseWithCommentsDto;
import com.todo.dto.UpdateTodoContentsRequestDto;
import com.todo.entity.Member;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> save(@Validated @RequestBody TodoRequestDto requestDto,
                                                @SessionAttribute(name = "member") Member member) {
        TodoResponseDto responseDto = todoService.save(member.getMemberId(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseWithCommentsDto> findById(@PathVariable Long id) {
        TodoResponseWithCommentsDto responseDto = todoService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TodoResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TodoResponseDto> todoResponseDtoPage = todoService.findAll(pageable);
        return new ResponseEntity<>(todoResponseDtoPage, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateContents(@PathVariable Long id,
                                               @Validated @RequestBody UpdateTodoContentsRequestDto requestDto,
                                               @SessionAttribute(name = "member") Member member) {
        todoService.updateContents(id, requestDto.getContents(), member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
