package com.security.demo.service;

import com.security.demo.web.dto.LoginRequest;
import com.security.demo.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface MemberService {

    String login(LoginRequest loginRequest);

    PasswordEncoder passwordEncoder();


    Optional<Member> findByMemberid(String account_id);

    Member signUp(Member member);
}
