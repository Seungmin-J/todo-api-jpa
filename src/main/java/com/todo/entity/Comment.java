package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)  // Member 는 Comment 를 여러 개 가질 수 있다 / Member 는 Todo 를 여러 개 가지지 못함 -> ManyToOne
    @JoinColumn(name = "member_id")     // member 테이블의 member_id 를 참조함 (FK 설정)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  // Todo 는 Comment 를 여러 개 가질 수 있다 / Comment 는 Todo 를 여러 개 가지지 못함 -> ManyToOne
    @JoinColumn(name = "todo_id")       // todo 테이블의 todo_id 를 참조함 (FK 설정)
    private Todo todo;

    @Column(nullable = false)
    private String contents;

    public Comment(Member member, Todo todo, String contents) {
        this.member = member;
        this.todo = todo;
        this.contents = contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
