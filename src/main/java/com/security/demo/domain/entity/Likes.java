package com.security.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Table
@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_no", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;


    /**
     * 게시글, 유저 정보를 받아 좋아요 정보를 저장
     * @param article 게시글
     * @param member 유저
     */
    public Likes(Article article, Member member) {
        this.article = article;
        this.member = member;
    }
}
