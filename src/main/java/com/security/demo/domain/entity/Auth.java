package com.security.demo.domain.entity;


import lombok.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Setter
@Getter
public class Auth extends User {

    private Long authNo;

    /**
     * Spring Security에서 제공하는 User 클래스를 Member로 커스텀하기 위해 설정
     */
    private Member member;

    public Auth(Member member) {
        super(member.getAccountNo(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getAccount_type().toString()));

        System.out.printf("SecurityUser member.memberNo = %s", member.getAccountNo());
        System.out.printf("SecurityUser member.password = %s", member.getPassword());
        System.out.printf("SecurityUser member.role = %s", member.getAccount_type());

        this.member = member;
    }

}
