package com.security.demo.web;

import com.security.demo.domain.Member;
import com.security.demo.common.role.Role;
import com.security.demo.service.MemberService;
import com.security.demo.web.dto.LoginRequest;
import com.security.demo.web.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("member", new LoginRequest());
        return mv;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        log.warn("접속한 ID : {}", loginRequest.getAccountId());
        log.warn("접속한 Password : {}", loginRequest.getPassword());

        TokenResponse token = new TokenResponse(memberService.login(loginRequest), "Bearer");

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public String signup(Member memberDTO) {
        return Objects.isNull(memberService.signUp(memberDTO).getMemberNo()) ? "redirect:/signup" : "redirect:/main";
    }

    @GetMapping("/info")
    public ResponseEntity<Member> getUserFromToken(HttpServletRequest request) {
        String accountId = (String) request.getAttribute("accountId");
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
