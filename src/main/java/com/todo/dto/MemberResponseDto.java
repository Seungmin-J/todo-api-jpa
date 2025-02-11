package com.todo.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long memberId;

    private final String memberName;

    private final String email;

    public MemberResponseDto(Long memberId, String memberName, String email) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
    }

    public static MemberResponseDto toUserDto(com.todo.entity.Member member) {
        return new MemberResponseDto(member.getMemberId(), member.getMemberName(), member.getEmail());
    }
}
