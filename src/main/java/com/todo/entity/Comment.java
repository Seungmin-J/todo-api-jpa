package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Column(nullable = false)
    private String contents;

    public Comment(Member member, Todo todo, String contents) {
        this.member = member;
        this.todo = todo;
        this.contents = contents;
    }
}
