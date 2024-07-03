package com.security.demo.mapper;

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
public class MemberRepositorySupportImpl implements MemberRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByAccountId(String accountId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.accountNo.eq(accountId))
                .fetchOne());
    }

    @Override
    public void updateNickname(String nickName) {
        jpaQueryFactory.update(member).where(member.nickname.eq("chris"))
                .set(member.nickname, nickName)
                .execute();
    }

    @Override
    public void deleteByMember_idx(Long memberNo) {
        jpaQueryFactory.delete(member)
                .where(member.memberNo.eq(memberNo))
                .execute();
    }

    @Override
    public List<Member> findByAccountId2(String accountNo) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.accountNo.eq(accountNo))
                .fetch();
    }
}
