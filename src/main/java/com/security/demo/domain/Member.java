package com.security.demo.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long member_idx;

    @Column(nullable = false)
    private String nickname;

    @NotNull
    @Column(nullable = false, name = "account_id")
    private String accountId;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role account_type;

    @Enumerated(EnumType.STRING)
    private Role_withdraw quit;


    @Builder
    public Member(String nickname, String accountId, String password, Role account_type, Role_withdraw quit) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.account_type = account_type;
        this.quit = quit;
    }
}
