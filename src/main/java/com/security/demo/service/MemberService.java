package com.security.demo.service;

import com.security.demo.domain.response.TokenResponse;
import com.security.demo.domain.request.LoginRequest;
import com.security.demo.domain.entity.Member;
import com.security.demo.common.config.JwtTokenProvider;
import com.security.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    private final Long expiredMs = 1000 * 60 * 60L;


    /**
     * 로그인 후 jwt token 생성 -> 가져오기
     * @param loginRequest
     * @return (String) Jwt Token
     */
    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByAccountId(loginRequest.getAccountId())
                .orElseThrow(() -> new NullPointerException("This user does not exist."));

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secretKey, expiredMs, member);
        return new TokenResponse(jwtTokenProvider.getToken(), "Bearer");
    }

    @Transactional(readOnly = true)
    public Member findByAccountId(String accountId) {
        return memberRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NullPointerException("This account_id is not exist."));
    }

    public Member signUp(Member memberDTO) {
        Member member = Member.builder()
                .accountNo(memberDTO.getAccountNo())
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
