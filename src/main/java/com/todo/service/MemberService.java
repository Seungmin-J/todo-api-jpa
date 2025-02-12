package com.todo.service;

import com.todo.config.PasswordEncoder;
import com.todo.dto.LoginRequestDto;
import com.todo.dto.MemberResponseDto;
import com.todo.entity.Member;
import com.todo.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto save(String memberName, String email, String password) {
        String encoded = passwordEncoder.encode(password);
        com.todo.entity.Member member = new com.todo.entity.Member(memberName, email, encoded);
        com.todo.entity.Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getMemberId(), savedMember.getMemberName(), savedMember.getEmail());
    }

    public MemberResponseDto findByMemberId(Long memberId) {
        com.todo.entity.Member member = memberRepository.findByMemberIdOrElseThrow(memberId);
        return new MemberResponseDto(member.getMemberId(), member.getMemberName(), member.getEmail());
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toUserDto)
                .toList();
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, String memberName, String email, String password) {
        Member member = memberRepository.findByMemberIdOrElseThrow(id);
        if (passwordEncoder.matches(password, member.getPassword())) {
            member.setMemberName(memberName);
            member.setEmail(email);
        }

        return new MemberResponseDto(member.getMemberId(), member.getMemberName(), member.getEmail());
    }

    @Transactional
    public void deleteMember(Long id) {

        Member member = memberRepository.findByMemberIdOrElseThrow(id);
        memberRepository.delete(member);
    }

    public void validateMember(LoginRequestDto requestDto, HttpSession session) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "일치하는 Email이 없습니다"));

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        session.setAttribute("member", member);
        session.setAttribute("sessionKey", member.getMemberId());
    }
}
