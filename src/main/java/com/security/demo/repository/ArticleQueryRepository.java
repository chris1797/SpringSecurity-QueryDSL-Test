package com.security.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Article> findByArticle_idx(Long articleNo) {
        return jpaQueryFactory.selectFrom(article)
                .where(article.articleNo.eq(articleNo))
                .fetch();
    }

    public Long deleteByArticleNo(Long articleNo) {
        return jpaQueryFactory.delete(article)
                .where(article.articleNo.eq(articleNo))
                .execute();
    }

    public List<Article> findByArticle_Idx(Long article_idx) {
        return jpaQueryFactory.selectFrom(article)
                .where(article.articleNo.eq(article_idx))
                .fetch();
    }

    public List<Article> findAll() {
        return jpaQueryFactory.select(article)
                .fetch();
    }
}
