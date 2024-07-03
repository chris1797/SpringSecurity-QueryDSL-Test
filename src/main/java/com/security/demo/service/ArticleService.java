package com.security.demo.service;

import com.security.demo.domain.entity.Article;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Boolean deleteArticle(Long articleNo) {
        Article article = articleRepository.findByArticleNo(articleNo);
        if (Objects.isNull(article)) return false;

        articleRepository.deleteByArticleNo(articleNo);
        return true;
    }


    @Transactional(readOnly = true)
    public Article getArticleDetail(Long articleNo, String authentication) throws Exception {
        String[] role = authentication.split(" ");
        if (role[0] == null || role[0].isEmpty()) throw new Exception("No authentication.");

        return articleRepository.findByArticleNo(articleNo);
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }


}
