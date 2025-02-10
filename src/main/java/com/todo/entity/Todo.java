package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

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
