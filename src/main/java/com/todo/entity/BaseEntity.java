package com.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // @CreatedDate -> 자동으로 생성된 시간 저장, updatable 속성을 false 로 두면 업데이트가 불가능
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // @LastModifiedDate -> 수정될 때 자동으로 수정할 때의 시간으로 저장됨
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
