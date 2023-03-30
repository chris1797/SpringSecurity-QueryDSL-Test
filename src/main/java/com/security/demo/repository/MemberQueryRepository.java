package com.security.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Member> findByAccountId(String accountId) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.accountId.eq(accountId))
                .fetch();
    }

    public void updateNickname(String nickName) {
        jpaQueryFactory.update(member).where(member.nickname.eq("chris"))
                .set(member.nickname, nickName)
                .execute();
    }

    public void deleteByMember_idx(Long member_idx) {
        jpaQueryFactory.delete(member)
                .where(member.member_idx.eq(member_idx))
                .execute();
    }
}
