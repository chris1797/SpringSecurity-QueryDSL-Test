package com.security.demo.mapper;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.QMember.member;


@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    /**
     * Bean으로 등록한 JPAQueryFactory를 생성자로 주입
     * @param queryFactory
     */
    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> findByAccountId(String accountId) {
        return queryFactory.selectFrom(member)
                .where(member.accountId.eq(accountId))
                .fetch();
    }


}
