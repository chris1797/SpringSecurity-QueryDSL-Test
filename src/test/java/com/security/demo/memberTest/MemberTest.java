package com.security.demo.memberTest;

import com.security.demo.common.config.JwtTokenProvider;
import com.security.demo.common.constants.Role;
import com.security.demo.domain.entity.Member;
import com.security.demo.domain.response.TokenResponse;
import com.security.demo.repository.MemberRepository;
import com.security.demo.service.MemberService;
import com.security.demo.domain.request.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @RunWith(SpringRunner.class) : JUnit4에서 SpringMvc - service 테스트를 하기 위해 선언
 */

@SpringBootTest
public class MemberTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    MemberRepository memberRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    public void signUpTest() {
        Member member =  Member.builder()
                .nickname("test nickName")
                .accountNo("test id")
                .password("1234")
                .account_type(Role.LESSEE)
                .build();
        Member resultMember = memberService.signUp(member);

        assertEquals(resultMember.getNickname(), "test nickName");
    }

    @Test
    @DisplayName("ID로 계정 찾기 테스트")
    public void getUserByIdTest() {

        String account_id = "chris";

        Member member = memberService.findByAccountId(account_id);
        assertEquals(member.getAccountNo(), "chris");
    }

    @Test
    @DisplayName("로그인 테스트")
    public void loginTest() {
        String userId = "chris";
        String userPwd = "test";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccountId(userId);
        loginRequest.setPassword(userPwd);

        TokenResponse result = memberService.login(loginRequest);
        assertEquals(result, is("test"));
    }
}
