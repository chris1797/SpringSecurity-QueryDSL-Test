package com.security.demo.service;

import com.security.demo.web.dto.LoginRequest;
import com.security.demo.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MemberService {

    String login(LoginRequest loginRequest);

    PasswordEncoder passwordEncoder();

    Member findByAccountId(String accountId);

    Member signUp(Member member);
}
