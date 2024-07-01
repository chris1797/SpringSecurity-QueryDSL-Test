package com.security.demo.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.demo.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.security.demo.domain.entity.QArticle.article;


@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public Article findByArticleNo(Long articleNo) {
        return jpaQueryFactory.selectFrom(article)
                .where(article.articleNo.eq(articleNo))
                .fetchOne();
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

    @Override
    public int save(Article article) {
        return (int) jpaQueryFactory.insert((EntityPath<?>) article)
                .execute();
    }
}
