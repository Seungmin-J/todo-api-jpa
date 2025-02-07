package com.todo.controller;

import com.todo.dto.DeleteRequestDto;
import com.todo.dto.UserRequestDto;
import com.todo.dto.UserResponseDto;
import com.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findByUserId(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.updateUser(id, requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody DeleteRequestDto requestDto) {
        userService.deleteUser(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
