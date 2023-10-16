package com.security.demo.mapper;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.Article;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.QArticle.article;

@Repository
public class ArticleRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositorySupport(JPAQueryFactory queryFactory) {
        super(Article.class);
        this.queryFactory = queryFactory;
    }

    public List<Article> findByArticle_Idx(Long article_idx) {
        return queryFactory.selectFrom(article)
                .where(article.articleNo.eq(article_idx))
                .fetch();
    }

    public List<Article> findAll() {
        return queryFactory.select(article)
                .fetch();
    }

}
