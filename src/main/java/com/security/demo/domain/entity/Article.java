package com.security.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tbl_article")
@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleNo;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    private int viewCnt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    Set<Likes> likes = new HashSet<>(); // 전체글 리스트에서 좋아요 수를 나타내기 위한 양방향 관계


    private LocalDateTime regDate; // 작성일

    private LocalDateTime uptDate; // 수정일

    private LocalDateTime delDate; // 삭제일

}
