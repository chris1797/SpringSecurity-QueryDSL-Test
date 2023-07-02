package com.security.demo.memberTest;

import com.security.demo.common.role.Role;
import com.security.demo.domain.Member;
import com.security.demo.repository.MemberRepository;
import com.security.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * @RunWith(SpringRunner.class) : JUnit4에서 SpringMvc - service 테스트를 하기 위해 선언
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    public void signUpTest() {
        Member member =  Member.builder()
                .nickname("test nickName")
                .accountId("test id")
                .password("1234")
                .account_type(Role.LESSEE)
                .build();
        Member resultMember = memberService.signUp(member);
        log.warn("result Member_idx :::: {}", resultMember.getMember_idx());

        assertEquals(resultMember.getNickname(), "test nickName");
    }
}
