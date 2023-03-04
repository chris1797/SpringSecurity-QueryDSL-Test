package com.security.demo.dto;

import com.security.demo.entity.Article;
import com.security.demo.entity.Member;
import com.security.demo.entity.Likes;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
public class ArticleDTO {

    private String title;
    private String content;
    private Member member;
    private Set<Likes> likes;


    public Article toEntity() throws Exception {
        return Article.builder()
                .title(title)
                .content(content)
                .member(member)
                .likes(likes)
                .build();
    }

}
