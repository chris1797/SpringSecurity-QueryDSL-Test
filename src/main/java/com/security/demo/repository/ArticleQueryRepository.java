package com.security.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Article> findByArticle_idx(Long article_idx) {
        return jpaQueryFactory.selectFrom(article)
                .where(article.article_idx.eq(article_idx))
                .fetch();
    }

    public void deleteByArticle_idx(Long article_idx) {
        jpaQueryFactory.delete(article)
                .where(article.article_idx.eq(article_idx))
                .execute();
    }
}
