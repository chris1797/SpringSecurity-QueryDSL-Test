package com.security.demo.controller;

import com.security.demo.domain.entity.Member;
import com.security.demo.common.constants.Role;
import com.security.demo.domain.request.SignUpRequest;
import com.security.demo.service.MemberService;
import com.security.demo.domain.request.LoginRequest;
import com.security.demo.domain.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok().body(memberService.login(loginRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(memberService.signUp(request));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Member> getUserFromToken(@PathVariable String accountId) {
        return ResponseEntity.ok().body(memberService.findByAccountId(accountId));
    }

    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("관리자", Role.ADMIN);
        map.put("판매자", Role.OWNER);
        map.put("사용자", Role.MEMBER);
        return map;
    }
}
