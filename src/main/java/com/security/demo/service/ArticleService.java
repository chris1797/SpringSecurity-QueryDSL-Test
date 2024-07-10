package com.security.demo.service;

import com.security.demo.domain.entity.Article;
import com.security.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Article getArticleDetail(Long articleNo, String authentication) throws Exception {
        String[] role = authentication.split(" ");
        if (role[0] == null || role[0].isEmpty()) throw new Exception("No authentication.");

        return articleRepository.findByArticleNo(articleNo);
    }

    public Page<Article> getArticleList(PageRequest pageRequest) {
        if (Objects.isNull(pageRequest)) pageRequest = PageRequest.of(0, 10);
        return articleRepository.findAll(pageRequest);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Boolean deleteArticle(Long articleNo) {
        Article article = articleRepository.findByArticleNo(articleNo);
        if (Objects.isNull(article)) return false;

        articleRepository.deleteByArticleNo(articleNo);
        return true;
    }


}
