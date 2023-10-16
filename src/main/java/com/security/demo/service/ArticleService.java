package com.security.demo.service;

import com.security.demo.domain.Article;
import com.security.demo.repository.ArticleQueryRepository;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;


    @Transactional
    public Boolean deleteArticle(Long articleNo) {
        Article article = articleRepository.findById(articleNo).orElseThrow(() -> new NullPointerException("articleNo is not exist."));
        if (Objects.isNull(article)) return false;

        articleRepository.deleteById(articleNo);
        return true;
    }


    @Transactional(readOnly = true)
    public Article getArticleDetail(Long article_idx, String authentication) throws Exception {
        String[] role = authentication.split(" ");
        if (role[0] == null || role[0].isEmpty()) throw new Exception("No authentication.");

        return articleRepository.findById(article_idx)
                .orElseThrow(() -> new NullPointerException("This article does not exist."));
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    public Boolean save(Article article) {
        return articleRepository.save(article).getArticleNo() > 0;
    }


}
