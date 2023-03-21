package com.security.demo.service;

import com.security.demo.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticle();

    Long writeArticle(Article article);

    void deleteArticle(Long article_idx);

    Article getArticleDetail(Long article_idx);
}
