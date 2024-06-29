package com.security.demo.controller;

import com.security.demo.domain.entity.Member;
import com.security.demo.common.role.Role;
import com.security.demo.service.MemberService;
import com.security.demo.domain.request.LoginRequest;
import com.security.demo.domain.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        log.warn("접속한 ID : {}", loginRequest.getAccountId());
        log.warn("접속한 Password : {}", loginRequest.getPassword());

        return ResponseEntity.ok().body(memberService.login(loginRequest));
    }

    @PostMapping("/signup")
    public String signup(Member memberDTO) {
        return Objects.isNull(memberService.signUp(memberDTO).getMemberNo()) ? "redirect:/signup" : "redirect:/main";
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Member> getUserFromToken(@PathVariable String accountId) {
        return ResponseEntity.ok().body(memberService.findByAccountId(accountId));
    }

    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("임차인", Role.LESSEE);
        map.put("임대인", Role.LESSOR);
        map.put("공인중개사", Role.REALTOR);
        return map;
    }
}
