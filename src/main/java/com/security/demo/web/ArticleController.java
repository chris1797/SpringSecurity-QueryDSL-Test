package com.security.demo.web;

import com.security.demo.domain.Article;
import com.security.demo.domain.Member;
import com.security.demo.service.ArticleService;
import com.security.demo.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final LikesService likeService;

    // 글전체리스트 보기
    @GetMapping("/")
    public String getArticleList(Model model) {
        model.addAttribute(articleService.getAllArticle());
        return "/article/articleList";
    }

    // 글작성폼
    @GetMapping("/write")
    public String getArticleWriteForm(Model model,
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
    @GetMapping("/view/{article_idx}")
    public String getArticleDetail(Model model,
                                   @PathVariable(name = "article_idx") Long article_idx) {
        model.addAttribute("article", articleService.getArticleDetail(article_idx));
        return "/article/detailForm";
    }

    // 글작성
    @PostMapping("/")
    public String ArticleWrite(Article article){
        return "redirect:/article/view?id=" + articleService.writeArticle(article);
    }

    // 글수정폼
    @GetMapping("/edit/{article_idx}")
    public String getArticleEditForm(Model model,
                            @RequestHeader String Authentication,
                            @PathVariable(name = "article_idx") Long article_idx) {
        /**
         * Authentication의 Role 권한이 외부사용자이면 list 페이지로 이동
         */
        String[] role = Authentication.split(" ");
        if(role[0] == null || role[0] == "") return "/article/articleList";
        model.addAttribute("article", articleService.getArticleDetail(article_idx));

        return "/article/editForm";
    }

    // 글수정
    @PatchMapping("/edit/{article_idx}")
    public ResponseEntity<Long> articleEdit(Model model, Article article) {
        return ResponseEntity.ok().body(articleService.writeArticle(article));
    }

    // 글삭제
    @DeleteMapping("/{article_idx}")
    public void articleDelete(@PathVariable(name = "article_idx") Long article_idx) {
        articleService.deleteArticle(article_idx);
    }

    // 좋아요
    @PostMapping("/like")
    public ResponseEntity<Boolean> addLike(Member member,
                           @RequestParam("article_idx") Long article_idx) {
        return ResponseEntity.ok().body(likeService.addLike(member, article_idx));
    }

}
