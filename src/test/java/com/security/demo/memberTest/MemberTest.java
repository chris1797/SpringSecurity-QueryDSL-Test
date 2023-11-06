package com.security.demo.memberTest;

import com.security.demo.common.config.JwtTokenProvider;
import com.security.demo.common.role.Role;
import com.security.demo.domain.Member;
import com.security.demo.repository.MemberQueryRepository;
import com.security.demo.repository.MemberRepository;
import com.security.demo.service.MemberService;
import com.security.demo.domain.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * @RunWith(SpringRunner.class) : JUnit4에서 SpringMvc - service 테스트를 하기 위해 선언
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberQueryRepository queryRepository;

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
        String userPwd = "123";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccountId(userId);
        loginRequest.setPassword(userPwd);

        String result = memberService.login(loginRequest);
        assertThat(result, is("123"));
    }
}
