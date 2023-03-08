package com.security.demo.controller;

import com.security.demo.entity.Article;
import com.security.demo.entity.Member;
import com.security.demo.service.ArticleService;
import com.security.demo.service.LikesService;
import com.security.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final LikesService likeService;


    // 글전체리스트 보기
    @GetMapping("/article")
    String getArticleList(Model model) {
        model.addAttribute(articleService.getAllArticle());
        return "/article/articleList";
    }

    // 글작성폼
    @GetMapping("/article/write")
    String getArticleWriteForm(Model model,
                             @RequestHeader String Authentication) {
        /**
         * Authentication의 Role 권한이 외부사용자이면 list 페이지로 이동
         */
        String[] role = Authentication.split(" ");
        if(role[0] == null || role[0].isEmpty()) {
            return "/article/articleList";
        }

        return "/article/writeForm";
    }

    // 글상세페이지
    @GetMapping("/article/view/{article_idx}")
    String getArticleDetail(Model model,
                          @PathVariable(name = "article_idx") Long article_idx) {
        model.addAttribute("article", articleService.getArticleDetail(article_idx));
        return "/article/detailForm";
    }

    // 글작성
    @PostMapping("/article")
    @Transactional
    String ArticleWrite(Article article){
        long article_idx = articleService.writeArticle(article);
        return "redirect:/article/view?id=" + article_idx;
    }

    // 글수정폼
    @GetMapping("/article/edit/{article_idx}")
    String getArticleEditForm(Model model,
                            @RequestHeader String Authentication,
                            @PathVariable(name = "article_idx") Long article_idx) {
        /**
         * Authentication의 Role 권한이 외부사용자이면 list 페이지로 이동
         */
        String[] role = Authentication.split(" ");
        if(role[0] == null || role[0] == "") {
            return "/article/articleList";
        }
        model.addAttribute("article", articleService.getArticleDetail(article_idx));

        return "/article/editForm";
    }

    // 글수정
    @PatchMapping("/article/edit/{article_idx}")
    @Transactional
    Map<String, Object> articleEdit(Model model, Article article) {
        Map<String, Object> map = new HashMap<>();
        articleService.writeArticle(article);
        map.put("updateChk", true);
        return map;
    }

    // 글삭제
    @DeleteMapping("/article/{article_idx}")
    Map<String, Object> ArticleDelete(Model model, Article article) {
        Map<String, Object> map = new HashMap<>();
        articleService.deleteArticle(article);
        map.put("updateChk", true);
        return map;
    }

    @PostMapping("/article/like")
    Map<String, Object> addLike(Model model,
                                Member member,
                                @RequestParam("article_id") Long article_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("addLikeChk", likeService.addLike(member, article_id));
        return map;
    }

}
