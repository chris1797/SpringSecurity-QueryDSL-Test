package com.security.demo.repository;

import com.security.demo.domain.entity.Article;
import com.security.demo.domain.entity.Likes;
import com.security.demo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    // user, article를 이용한 좋아요 체크를 위함
    Optional<Likes> findByMemberAndArticle(Member member, Article article);
}
