package com.security.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -589102410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final NumberPath<Long> article_idx = createNumber("article_idx", Long.class);

    public final StringPath content = createString("content");

    public final DatePath<java.util.Date> delete_date = createDate("delete_date", java.util.Date.class);

    public final DatePath<java.util.Date> last_date = createDate("last_date", java.util.Date.class);

    public final SetPath<Likes, QLikes> likes = this.<Likes, QLikes>createSet("likes", Likes.class, QLikes.class, PathInits.DIRECT2);

    public final QMember member;

    public final StringPath title = createString("title");

    public final DatePath<java.util.Date> write_date = createDate("write_date", java.util.Date.class);

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

