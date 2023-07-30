package com.security.demo.articleTest;

import com.security.demo.common.role.Role;
import com.security.demo.domain.Member;
import com.security.demo.repository.MemberQueryRepository;
import com.security.demo.repository.MemberRepository;
import com.security.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.security.demo.common.role.Role.REALTOR;
import static com.security.demo.common.role.Role_withdraw.ACTIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
public class QuerydslTest {

    @Autowired
    MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;


    @After
    public void afterTest() throws Exception {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원가입 테스트1")
    public void querydslTest() {
        // given
        Member member = Member.builder()
                .accountId("jaehun")
                .account_type(REALTOR)
                .nickname("chris")
                .password("123")
                .quit(ACTIVE)
                .build();
        memberRepository.save(member);

        // when
        List<Member> result = memberQueryRepository.findByAccountId("jaehun");

        // then
        assertEquals(1, result.size());
        assertEquals(result.get(0).getNickname(), "chris");
    }

    @Test
    @Transactional
    @DisplayName("회원가입 테스트2")
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
