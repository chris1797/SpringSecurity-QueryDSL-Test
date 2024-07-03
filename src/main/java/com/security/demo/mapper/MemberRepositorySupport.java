package com.security.demo.mapper;

import com.security.demo.domain.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositorySupport {

    Optional<Member> findByAccountId(String accountId);

    void updateNickname(String nickName);
    void deleteByMember_idx(Long memberNo);
    List<Member> findByAccountId2(String accountNo);
}
