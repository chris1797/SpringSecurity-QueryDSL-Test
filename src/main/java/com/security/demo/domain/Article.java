package com.security.demo.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_idx;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private int viewCnt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    Set<Likes> likes = new HashSet<>(); // 전체글 리스트에서 좋아요 수를 나타내기 위한 양방향 관계

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date write_date; // 작성일

    @Temporal(TemporalType.DATE)
    private Date last_date; // 수정일

    @Temporal(TemporalType.DATE)
    private Date delete_date; // 삭제일


    @Builder
    public Article(String title, String content, Set<Likes> likes, Member member) throws Exception {
        /**
         * Board 엔티티의 Not null인 컬럼들이 null일 경우 예외처리
         */
        if(title.isEmpty()) throw new Exception("Title is null");
        if(content.isEmpty()) throw new Exception("Content is null");
        if(member == null) throw new Exception("User is null");

        this.title = title;
        this.likes = likes;
        this.content = content;
        this.member = member;
    }
}
