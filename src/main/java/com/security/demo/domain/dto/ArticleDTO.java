package com.security.demo.domain.dto;

import com.security.demo.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
