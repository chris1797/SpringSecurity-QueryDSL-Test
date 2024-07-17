package com.security.demo.domain.entity;

import com.security.demo.common.constants.Role;
import com.security.demo.common.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long memberNo;

    private String nickname;

    private String accountNo;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role account_type;

    @Enumerated(EnumType.STRING)
    private Status quit;

    private LocalDateTime regDate;


    @Builder
    public Member(Long memberNo, String nickname, String accountNo, String password, Role account_type, Status quit) {
        this.memberNo = memberNo;
        this.nickname = nickname;
        this.accountNo = accountNo;
        this.password = password;
        this.account_type = account_type;
        this.quit = quit;
        this.regDate = LocalDateTime.now();
    }
}
