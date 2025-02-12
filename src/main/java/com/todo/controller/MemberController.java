package com.todo.controller;

import com.todo.dto.*;
import com.todo.entity.Member;
import com.todo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> save(@Validated @RequestBody MemberRequestDto requestDto) {
        MemberResponseDto memberResponseDto = memberService.save(requestDto.getMemberName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findByMemberId(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findByMemberId(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<MemberResponseDto> updateMember(@Validated @RequestBody MemberRequestDto requestDto,
                                                          @SessionAttribute("member") Member member) {
        MemberResponseDto memberResponseDto = memberService.updateMember(requestDto, member);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginRequestDto requestDto, HttpSession session) {
        memberService.login(requestDto, session);
        return "로그인 성공";
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
