package com.security.demo.service;

import com.security.demo.domain.Article;
import com.security.demo.domain.Likes;
import com.security.demo.domain.Member;
import com.security.demo.repository.ArticleRepository;
import com.security.demo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;


    /**
     * 좋아요 추가
     * @param member
     * @param article_id
     * @return Boolean
     */
    @Transactional
    public boolean addLike(Member member, Long article_id) {
        log.warn("addLike() Parameter :: {}, {}", member, article_id);

        Article article = articleRepository.findById(article_id)
                .orElseThrow(() -> new NullPointerException("This article does not exist."));

        if (isNotAlreadyLike(member, article)) {
            likeRepository.save(new Likes(article, member));
            return true;
        }

        return false;
    }

    /**
     * @description: 유저의 해당 article 좋아요 여부 체크
     */
    @Transactional(readOnly = true)
    public boolean isNotAlreadyLike(Member member, Article article) {
        return likeRepository.findByMemberAndArticle(member, article).isPresent();
    }
}
