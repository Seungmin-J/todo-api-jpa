package com.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    public Member() {}

    public Member(String memberName, String email, String password) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }
}

