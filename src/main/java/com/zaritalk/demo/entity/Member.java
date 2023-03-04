package com.zaritalk.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long member_idx;

    @Column(nullable = false)
    private String nickname;

    @NotNull
    @Column(nullable = false, name = "account_id")
    private String memberid;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role account_type;

    @Enumerated(EnumType.STRING)
    private Role_withdraw quit;


    @Builder
    public Member(String nickname, String memberid, String password, Role account_type, Role_withdraw quit) {
        /**
         * Member 엔티티의 Not null인 컬럼들이 null일 예외 처리
         */
        this.memberid = memberid;
        this.nickname = nickname;
        this.password = password;
        this.account_type = account_type;
        this.quit = quit;
    }
}
