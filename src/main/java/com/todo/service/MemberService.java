package com.todo.service;

import com.todo.config.PasswordEncoder;
import com.todo.dto.LoginRequestDto;
import com.todo.dto.MemberRequestDto;
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

    // PasswordEncoder 의 encode 메서드로 password 암호화하여 Member 를 MemberRepository 에 저장
    @Transactional
    public MemberResponseDto save(String memberName, String email, String password) {
        String encoded = passwordEncoder.encode(password);
        Member member = new Member(memberName, email, encoded);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getMemberId(), savedMember.getMemberName(), savedMember.getEmail());
    }

    public MemberResponseDto findByMemberId(Long memberId) {
        Member member = memberRepository.findByMemberIdOrElseThrow(memberId);
        return new MemberResponseDto(member.getMemberId(), member.getMemberName(), member.getEmail());
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toUserDto)
                .toList();
    }

    @Transactional
    public MemberResponseDto updateMember(MemberRequestDto memberRequestDto, Member sessionMember) {
        Member member = memberRepository.findByMemberIdOrElseThrow(sessionMember.getMemberId());

        // 요청받은 이메일을 사용하는 사용자가 있는지 확인
        if (memberRepository.findByEmail(memberRequestDto.getEmail()).isEmpty()) {
            // 중복 이메일 없으면 setEmail, setMemberName
            member.setEmail(memberRequestDto.getEmail());
            member.setMemberName(memberRequestDto.getMemberName());
            return new MemberResponseDto(member.getMemberId(), member.getMemberName(), member.getEmail());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하는 email 입니다");
        }
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findByMemberIdOrElseThrow(id);
        memberRepository.delete(member);
    }

    // 이메일로 Member 확인/비밀번호 검증 후 session에 Member 전달
    public void login(LoginRequestDto requestDto, HttpSession session) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "일치하는 사용자가 없습니다"));

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        session.setAttribute("member", member);
    }
}
