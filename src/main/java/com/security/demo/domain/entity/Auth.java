package com.security.demo.domain.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;

@Table
@Data
@NoArgsConstructor
public class Auth {
//public class Auth extends User {

    /**
     * Spring Security에서 제공하는 User 클래스를 Member로 커스텀하기 위해 설정
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authNo;



//    public Auth(Member member) {
//        super(member.getAccountNo(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getAccount_type().toString()));
//
//        log.info("SecurityUser member.memberNo = {}", member.getAccountNo());
//        log.info("SecurityUser member.password = {}", member.getPassword());
//        log.info("SecurityUser member.role = {}", member.getAccount_type());
//
//        this.member = member;
//    }

}
