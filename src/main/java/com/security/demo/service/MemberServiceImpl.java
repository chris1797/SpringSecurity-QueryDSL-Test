package com.security.demo.service;

import com.security.demo.dto.LoginRequest;
import com.security.demo.dto.MemberDTO;
import com.security.demo.entity.Member;
import com.security.demo.entity.Role_withdraw;
import com.security.demo.config.JwtTokenProvider;
import com.security.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 * 60L;


    @Override
    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberid(loginRequest.getMemberid())
                .orElseThrow(() -> new NullPointerException("해당 유저정보가 없습니다."));

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secretKey, expiredMs, member);
        return jwtTokenProvider.getToken();
    }


    @Override
    public Optional<Member> findByMemberid(String member_id) {
        return memberRepository.findByMemberid(member_id);
    }

    @Override
    public Member signUp(MemberDTO memberDTO) {
        Member member2 = memberDTO.toEntity();
        Member member = Member.builder()
                .memberid(memberDTO.getMemberid())
                .password(passwordEncoder().encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .account_type(memberDTO.getAccount_type())
                .quit(Role_withdraw.ACTIVE) // 활성유저로 회원가입 시키기
                .build();

        return memberRepository.save(member);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
