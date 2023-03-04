package com.zaritalk.demo.repository;

import com.zaritalk.demo.entity.Article;
import com.zaritalk.demo.entity.Likes;
import com.zaritalk.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Likes, Long> {

    // user, article를 이용한 좋아요 체크를 위함
    Optional<Likes> findByMemberAndArticle(Member member, Article article);
}
