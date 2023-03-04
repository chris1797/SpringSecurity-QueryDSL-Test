package com.zaritalk.demo.service;

import com.zaritalk.demo.config.JwtTokenProvider;
import com.zaritalk.demo.dto.MemberDTO;
import com.zaritalk.demo.entity.Member;
import com.zaritalk.demo.entity.Role_withdraw;
import com.zaritalk.demo.repository.MemberRepository;
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
    public String login(String userName, String password) {
        return JwtTokenProvider.createToken(userName, secretKey, expiredMs);
    }

    @Override
    public String createToken(Member member) {
        Optional<Member> _member = memberRepository.findByMemberid(member.getMemberid());
        return _member.get().getAccount_type() + " " + _member.get().getMember_idx();
    }


    @Override
    public Optional<Member> findByMemberid(String member_id) {
        return memberRepository.findByMemberid(member_id);
    }

    @Override
    public Member signUp(MemberDTO member) {
        Member _member = Member.builder()
                .memberid(member.getMemberid())
                .password(passwordEncoder().encode(member.getPassword()))
                .nickname(member.getNickname())
                .account_type(member.getAccount_type())
                .quit(Role_withdraw.ACTIVE) // 활성유저로 회원가입 시키기
                .build();

        return memberRepository.save(_member);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
