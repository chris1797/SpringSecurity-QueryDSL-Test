package com.security.demo.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.security.demo.domain.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepository implements MemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Member> findByAccountId(String accountId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.accountNo.eq(accountId))
                .fetchOne());
    }

    public void updateNickname(String nickName) {
        jpaQueryFactory.update(member).where(member.nickname.eq("chris"))
                .set(member.nickname, nickName)
                .execute();
    }

    public void deleteByMember_idx(Long memberNo) {
        jpaQueryFactory.delete(member)
                .where(member.memberNo.eq(memberNo))
                .execute();
    }

    public List<Member> findByAccountId2(String accountNo) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.accountNo.eq(accountNo))
                .fetch();
    }

    public Member save(Member member) {
        jpaQueryFactory.insert((EntityPath<?>) member)
                .execute();
        return member;
    }
}
