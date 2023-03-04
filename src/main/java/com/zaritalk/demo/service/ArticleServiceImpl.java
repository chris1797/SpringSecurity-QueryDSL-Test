package com.zaritalk.demo.service;

import com.zaritalk.demo.entity.Article;
import com.zaritalk.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Article getArticleDetail(Long article_idx) {
        return articleRepository.findById(article_idx).get();
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public long writeArticle(Article article) {
        return articleRepository.save(article).getArticle_idx();
    }


}
