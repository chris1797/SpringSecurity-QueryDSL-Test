package com.security.demo.service;

import com.security.demo.domain.entity.Article;
import com.security.demo.domain.entity.Likes;
import com.security.demo.domain.entity.Member;
import com.security.demo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikeRepository likeRepository;
    private final ArticleQueryRepository articleRepository;


    /**
     * 좋아요 추가
     * @param member
     * @param article_id
     * @return Boolean
     */
    public boolean addLike(Member member, Long article_id) {
        Article article = articleRepository.findByArticleNo(article_id);

        if (AlreadyLikeCheck(member, article)) return false;

        likeRepository.save(new Likes(article, member));
        return true;
    }

    /**
     * @description: 유저의 해당 article 좋아요 여부 체크
     */
    private boolean AlreadyLikeCheck(Member member, Article article) {
        return likeRepository.findByMemberAndArticle(member, article).isPresent();
    }
}
