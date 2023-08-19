package com.security.demo.domain.dto;

import com.security.demo.domain.Article;
import com.security.demo.domain.Likes;
import com.security.demo.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ArticleDTO {

    private Long articleNo;
    private String title;
    private String content;
    private Member member;
    private int viewCnt;
    private LocalDateTime regDate;
    private LocalDateTime uptDate;
    private LocalDateTime delDate;

}
