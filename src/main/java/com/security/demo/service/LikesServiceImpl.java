package com.security.demo.service;

import com.security.demo.entity.Article;
import com.security.demo.entity.Likes;
import com.security.demo.entity.Member;
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


    /**
     * article_id에 해당하는 글에 user의 좋아요 추가
     */
    @Transactional
    @Override
    public boolean addLike(Member member, Long article_id) {
        Optional<Article> article = articleRepository.findById(article_id);

        if(!article.isPresent()) throw new IllegalArgumentException();
        if(isNotAlreadyLike(member, article.get())) {
            likeRepository.save(new Likes(article.get(), member));
            return true;
        }
        return false;
    }

    /**
     * user의 해당 article 좋아요 여부 체크
     */
    @Transactional
    private boolean isNotAlreadyLike(Member member, Article article) {
        return likeRepository.findByMemberAndArticle(member, article).isPresent();
    }
}
