package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "todo")
@EntityListeners(AuditingEntityListener.class)
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    // Member 는 Todo 를 여러 개 가질 수 있음 -> ManyToOne
    // member_id FK 관리
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // Todo 는 Comment 를 여러 개 가질 수 있음 -> OneToMany
    // mappedBy = "todo" <- Comment 의 todo 필드를 가리킴. 연관관계의 주인은 Comment
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Todo() {}

    public Todo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
