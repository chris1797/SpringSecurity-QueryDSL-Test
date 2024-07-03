package com.security.demo.repository;

import com.security.demo.domain.entity.Article;

import java.util.List;

public interface ArticleCustomRepository {

    Article findByArticleNo(Long articleNo);
    Long deleteByArticleNo(Long articleNo);
    List<Article> findAll();
}
