package com.security.demo.mapper;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.entity.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleRepositorySupportImpl implements ArticleRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Article findByArticleNo(Long articleNo) {
        return jpaQueryFactory.selectFrom(article)
                .where(article.articleNo.eq(articleNo))
                .fetchOne();
    }

    @Override
    public Long deleteByArticleNo(Long articleNo) {
        return jpaQueryFactory.delete(article)
                .where(article.articleNo.eq(articleNo))
                .execute();
    }

    @Override
    public List<Article> findAll() {
        return jpaQueryFactory.select(article)
                .fetch();
    }
}
