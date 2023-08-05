package com.security.demo.service;

import com.security.demo.repository.MemberQueryRepository;
import com.security.demo.web.dto.LoginRequest;
import com.security.demo.domain.Member;
import com.security.demo.common.config.JwtTokenProvider;
import com.security.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository queryRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Value("${jwt.secret}")
    private String secretKey;
    private final Long expiredMs = 1000 * 60 * 60L;


    /**
     * 로그인 후 jwt token 생성 -> 가져오기
     * @param loginRequest
     * @return (String) Jwt Token
     */
    @Transactional(readOnly = true)
    public String login(LoginRequest loginRequest) {
        Member member = queryRepository.findByAccountId(loginRequest.getAccountId());
//                .orElseThrow(() -> new NullPointerException("This user does not exist."));

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secretKey, expiredMs, member);
        return jwtTokenProvider.getToken();
    }

    @Transactional(readOnly = true)
    public Member findByAccountId(String account_id) {
        return queryRepository.findByAccountId(account_id);
//                .orElseThrow(() -> new NullPointerException("This account_id is not exist."));
    }

    @Transactional
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


    private PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
