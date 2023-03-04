package com.zaritalk.demo.service;

import com.zaritalk.demo.config.LoginRequest;
import com.zaritalk.demo.dto.MemberDTO;
import com.zaritalk.demo.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface MemberService {

    String login(String userName, String password);

    String createToken(Member member);

    PasswordEncoder passwordEncoder();

    Optional<Member> findByMemberid(String account_id);

    Member signUp(MemberDTO member);
}
