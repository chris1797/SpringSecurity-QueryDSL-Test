package com.security.demo.service;

import com.security.demo.domain.Article;
import com.security.demo.domain.Likes;
import com.security.demo.domain.Member;
import com.security.demo.repository.ArticleRepository;
import com.security.demo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{

    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public boolean addLike(Member member, Long article_id) {
        Article article = articleRepository.findById(article_id)
                .orElseThrow(() -> new NullPointerException("This article does not exist."));

        if(isNotAlreadyLike(member, article)) {
            likeRepository.save(new Likes(article, member));
            return true;
        }
        return false;
    }

    /**
     * 유저의 해당 article 좋아요 여부 체크
     */
    @Override
    public boolean isNotAlreadyLike(Member member, Article article) {
        return likeRepository.findByMemberAndArticle(member, article).isPresent();
    }
}
