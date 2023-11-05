package com.security.demo.articleTest;

import com.security.demo.domain.Article;
import com.security.demo.repository.ArticleQueryRepository;
import com.security.demo.repository.ArticleRepository;
import com.security.demo.service.ArticleService;
import com.security.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    MemberService memberService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleQueryRepository queryRepository;


//    @After
//    public void afterTest() throws Exception {
//        articleRepository.deleteAllInBatch();
//    }

    @Test
    @Transactional
    public void writeArticleTest() throws Exception {
        Article article = Article.builder()
                .title("test title")
                .content("test content")
                .member(memberService.findByAccountId("chris"))
                .build();
        Boolean result = articleService.save(article);

        assertThat(result).isTrue();
    }

    @Test
    @Transactional
    @DisplayName("게시글리스트 테스트")
    public void getArticleListTest() {
        List<Article> list = articleService.getAllArticle();

//        assertEquals(list.get(0).getTitle(), "test");
    }

//    @Test
//    @DisplayName("게시글 상세 조회 테스트")
//    public void getArticleDetailTest() throws Exception {
//        String auth = "TestAuthCode";
//        Article result = articleService.getArticleDetail(3L, auth);
//
//        assertThat(result.getArticleNo()).isEqualTo(3L);
//    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @DisplayName("게시글 삭제 테스트")
    public void removeArticleTest() {

        Long result = queryRepository.deleteByArticleNo(3L);

        assertThat(result).isEqualTo(1);
    }
}
