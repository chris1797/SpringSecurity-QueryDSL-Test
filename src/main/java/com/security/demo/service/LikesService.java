package com.security.demo.service;

import com.security.demo.domain.entity.Article;
import com.security.demo.domain.entity.Likes;
import com.security.demo.domain.entity.Member;
import com.security.demo.repository.ArticleRepository;
import com.security.demo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;


    /**
     * 좋아요 추가
     *
     * @param member 유저
     * @param article_id 게시글 번호
     * @return Boolean
     */
    public Likes addLike(Member member, Long article_id) {
        Article article = articleRepository.findByArticleNo(article_id);
        if (AlreadyLikeCheck(member, article)) throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");

        return likeRepository.save(new Likes(article, member));
    }

    /**
     * @description: 유저의 해당 article 좋아요 여부 체크
     */
    private Boolean AlreadyLikeCheck(Member member, Article article) {
        return likeRepository.findByMemberAndArticle(member, article).isPresent();
    }
}
