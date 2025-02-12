package com.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false, unique = true)
    private String email;

    // Member 는 Todo 를 여러 개 가질 수 있음 -> OneToMany
    // mappedBy = "member" <- Todo 의 member 필드를 가리킴. 연관관계의 주인은 Todo
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    // Member 는 Comment 를 여러 개 가질 수 있음 -> OneToMany
    // mappedBy = "member" <- Comment 의 member 필드를 가리킴. 연관관계의 주인은 Comment
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

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