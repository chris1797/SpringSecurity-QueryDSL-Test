package com.security.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likes_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_idx", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", nullable = false)
    private Member member;


    public Likes(Article article, Member member) {
        this.article = article;
        this.member = member;
    }
}
