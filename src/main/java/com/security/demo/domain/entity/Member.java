package com.security.demo.domain.entity;

import com.security.demo.common.constants.Role;
import com.security.demo.common.constants.MemberStatus;
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
    private Long id;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    private LocalDateTime createdAt;


    @Builder
    public Member(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.memberStatus = MemberStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }
}
