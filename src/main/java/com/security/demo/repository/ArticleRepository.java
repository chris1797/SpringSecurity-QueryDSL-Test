package com.security.demo.repository;

import com.security.demo.entity.Article;
import com.security.demo.mapper.ArticleMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleMapper {
}
