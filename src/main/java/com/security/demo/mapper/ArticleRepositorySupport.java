package com.security.demo.mapper;

import com.security.demo.domain.entity.Article;

import java.util.List;

public interface ArticleRepositorySupport {

    Article findByArticleNo(Long articleNo);
    Long deleteByArticleNo(Long articleNo);
    List<Article> findAll();
}
