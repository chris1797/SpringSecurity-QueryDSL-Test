package com.security.demo.repository;

import com.security.demo.domain.entity.Article;

public interface ArticleCustomRepository {
    int save(Article article);
}
