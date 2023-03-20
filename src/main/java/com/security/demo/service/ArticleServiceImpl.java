package com.security.demo.service;

import com.security.demo.entity.Article;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    @Transactional
    @Override
    public void deleteArticle(Long article_idx) {
        articleRepository.deleteById(article_idx);
    }

    @Transactional
    @Override
    public Article getArticleDetail(Long article_idx) {
        return articleRepository.findById(article_idx).get();
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    @Override
    public Long writeArticle(Article article) {
        return articleRepository.save(article).getArticle_idx();
    }


}
