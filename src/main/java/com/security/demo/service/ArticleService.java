package com.security.demo.service;

import com.security.demo.domain.Article;
import com.security.demo.repository.ArticleQueryRepository;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;


    @Transactional
    public void deleteArticle(Long article_idx) {
        articleRepository.deleteById(article_idx);
    }


    @Transactional(readOnly = true)
    public Article getArticleDetail(Long article_idx) {
        Article article = articleRepository.findById(article_idx)
                .orElseThrow(() -> new NullPointerException("This article does not exist."));
        return article;
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    public Long save(Article article) {
        return articleRepository.save(article).getArticle_idx();
    }


}
