package com.security.demo.articleTest;

import com.security.demo.domain.Member;
import com.security.demo.repository.MemberQueryRepository;
import com.security.demo.repository.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.security.demo.domain.Role.REALTOR;
import static com.security.demo.domain.Role_withdraw.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuerydslTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;


    @After
    public void afterTest() throws Exception {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원가입 테스트")
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
}
