package com.security.demo.repository;

import com.security.demo.domain.Article;
import com.security.demo.domain.Likes;
import com.security.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    // user, article를 이용한 좋아요 체크를 위함
    Optional<Likes> findByMemberAndArticle(Member member, Article article);
}
