package com.security.demo.service;

import com.security.demo.domain.entity.Article;
import com.security.demo.queryDsl.ArticleRepositorySupport;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleRepositorySupport dao;


    public Boolean deleteArticle(Long articleNo) {
        Article article = articleRepository.findById(articleNo).orElseThrow(() -> new NullPointerException("articleNo is not exist."));
        if (Objects.isNull(article)) return false;

        articleRepository.deleteById(articleNo);
        return true;
    }


    @Transactional(readOnly = true)
    public Article getArticleDetail(Long articleNo, String authentication) throws Exception {
        String[] role = authentication.split(" ");
        if (role[0] == null || role[0].isEmpty()) throw new Exception("No authentication.");

        return articleRepository.findById(articleNo)
                .orElseThrow(() -> new NullPointerException("This article does not exist."));
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    public Boolean save(Article article) {
        return articleRepository.save(article).getArticleNo() > 0;
    }


}
