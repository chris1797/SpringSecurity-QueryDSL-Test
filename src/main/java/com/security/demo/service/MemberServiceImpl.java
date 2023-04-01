package com.security.demo.service;

import com.security.demo.web.dto.LoginRequest;
import com.security.demo.domain.Member;
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
        Member member = memberRepository.findByAccountId(loginRequest.getAccountId())
                .orElseThrow(() -> new NullPointerException("This user does not exist."));

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secretKey, expiredMs, member);
        return jwtTokenProvider.getToken();
    }

    @Override
    public Member findByAccountId(String account_id) {
        Member member = memberRepository.findByAccountId(account_id)
                .orElseThrow(() -> new NullPointerException("This account_id is not exist."));
        return member;
    }

    @Override
    public Member signUp(Member memberDTO) {
        Member member = Member.builder()
                .accountId(memberDTO.getAccountId())
                // password encode
                .password(passwordEncoder().encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .account_type(memberDTO.getAccount_type())
                .build();

        return memberRepository.save(member);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
