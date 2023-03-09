package com.security.demo.service;

import com.security.demo.dto.MemberDTO;
import com.security.demo.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface MemberService {

    String login(String memberid, String password);

    PasswordEncoder passwordEncoder();

    Optional<Member> findByMemberid(String account_id);

    Member signUp(MemberDTO member);
}
