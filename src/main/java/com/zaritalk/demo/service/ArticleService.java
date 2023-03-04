package com.zaritalk.demo.service;

import com.zaritalk.demo.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticle();

    long writeArticle(Article article);

    void deleteArticle(Article article);

    Article getArticleDetail(Long article_idx);
}
