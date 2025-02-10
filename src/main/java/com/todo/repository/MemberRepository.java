package com.todo.repository;

import com.todo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByMemberIdOrElseThrow(Long memberId) {
        return findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다"));
    }

    Optional<Member> findByEmail(String email);
}
