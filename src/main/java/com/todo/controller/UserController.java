package com.todo.controller;

import com.todo.dto.UserRequestDto;
import com.todo.dto.UserResponseDto;
import com.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.save(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

}
