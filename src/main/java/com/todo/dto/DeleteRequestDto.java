package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeleteRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 6)
    private final String password;

    public DeleteRequestDto(String password) {
        this.password = password;
    }
}
