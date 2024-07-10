package com.security.demo.controller;

import com.security.demo.domain.entity.Article;
import com.security.demo.domain.entity.Likes;
import com.security.demo.domain.entity.Member;
import com.security.demo.service.ArticleService;
import com.security.demo.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final LikesService likeService;

    @GetMapping("/")
    public ResponseEntity<Page<Article>> getArticleList(PageRequest pageRequest) {
        return ResponseEntity.ok(articleService.getArticleList(pageRequest));
    }

    @PostMapping("/save")
    public ResponseEntity<Article> ArticleWrite(Article article){
        return ResponseEntity.ok(articleService.save(article));
    }

    @GetMapping("/{article_idx}")
    public ResponseEntity<Article> getArticleEditForm(Model model,
                            @RequestHeader String authentication,
                            @PathVariable(name = "article_idx") Long article_idx) throws Exception {
        // * Authentication Role 권한이 외부 사용자라면 Exception
        return ResponseEntity.ok().body(articleService.getArticleDetail(article_idx, authentication));
    }

    @PatchMapping("/edit/{article_idx}")
    public ResponseEntity<Article> articleEdit(Model model, Article article) {
        return ResponseEntity.ok().body(articleService.save(article));
    }

    @DeleteMapping("/{article_idx}")
    public ResponseEntity<Boolean> articleDelete(@PathVariable(name = "article_idx") Long article_idx) {
        return ResponseEntity.ok().body(articleService.deleteArticle(article_idx));
    }

    @PostMapping("/like")
    public ResponseEntity<Likes> addLike(Member member,
                                         @RequestParam("article_id") Long article_id) {
        return ResponseEntity.ok().body(likeService.addLike(member, article_id));
    }

}
