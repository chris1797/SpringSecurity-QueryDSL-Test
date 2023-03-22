package com.security.demo.web;

import com.security.demo.domain.Member;
import com.security.demo.domain.Role;
import com.security.demo.service.MemberService;
import com.security.demo.web.dto.LoginRequest;
import com.security.demo.web.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
        System.out.println("접속한 ID : " + loginRequest.getMemberid());
        System.out.println("접속한 Password : " + loginRequest.getPassword());
        return ResponseEntity.ok().body(new TokenResponse(memberService.login(loginRequest), "Bearer"));
    }


    @GetMapping("/signup")
    public ModelAndView signupForm(Model model) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("member", new Member());

        return mv;
    }

    @PostMapping("/signup")
    public String signup(Model model, Member member) {
        try {
            memberService.signUp(member);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup";
        }

        return "redirect:/login";
    }

    @GetMapping("/info")
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
