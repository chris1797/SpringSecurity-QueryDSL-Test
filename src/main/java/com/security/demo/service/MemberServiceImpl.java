package com.security.demo.service;

import com.security.demo.config.LoginRequest;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 * 60L;


    @Override
    public String login(String memberid, String password) {
        return null;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return jwtTokenProvider.createToken(loginRequest);
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
