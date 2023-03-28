package com.security.demo.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Getter
@Setter
public class SecurityUser extends User {

    /**
     * Spring Security에서 제공하는 User 클래스를 Member로 커스텀하기 위해 설정
     */

    private Member member;

    public SecurityUser(Member member) {
        super(member.getAccountId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getAccount_type().toString()));

        log.info("SecurityUser member.memberid = {}", member.getAccountId());
        log.info("SecurityUser member.password = {}", member.getPassword());
        log.info("SecurityUser member.role = {}", member.getAccount_type());

        this.member = member;
    }

}
