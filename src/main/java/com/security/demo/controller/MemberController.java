package com.security.demo.controller;

import com.security.demo.config.LoginRequest;
import com.security.demo.config.TokenResponse;
import com.security.demo.dto.MemberDTO;
import com.security.demo.entity.Member;
import com.security.demo.entity.Role;
import com.security.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("member", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {

        System.out.println("접속한 ID : " + loginRequest.getMemberid());
        System.out.println("접속한 Password : " + loginRequest.getPassword());

        return ResponseEntity.ok().body(new TokenResponse(memberService.login(loginRequest), "Bearer"));
    }


    @GetMapping("/signup")
    String signupForm(Model model) {
        model.addAttribute("member", new MemberDTO());

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, MemberDTO member) {
        try {
            memberService.signUp(member);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup";
        }

        return "redirect:/login";
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<Member> getUserFromToken(HttpServletRequest request) {
        String memberid = (String) request.getAttribute("memberid");
        Optional<Member> member = memberService.findByMemberid((String) request.getAttribute("memberid"));
        return ResponseEntity.ok().body(member.get());
    }

    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("임차인", Role.ROLE_LESSEE);
        map.put("임대인", Role.ROLE_LESSOR);
        map.put("공인중개사", Role.ROLE_REALTOR);
        return map;
    }
}
