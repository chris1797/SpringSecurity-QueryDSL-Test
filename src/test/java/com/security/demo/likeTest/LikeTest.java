package com.security.demo.likeTest;

import com.security.demo.domain.entity.Article;
import com.security.demo.domain.entity.Member;
import com.security.demo.service.LikesService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @ExtendWith(SpringExtension.class) : 확장을 선언적으로 등록
 */
//@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureMockMvc
public class LikeTest {

    LikesService likeService;

    MockMvc mockMvc;

//    @Test
//    @DisplayName("좋아요 추가 테스트")
//    void addlikeTest() throws Exception {
//        Article article = createArticle();
//
//        mockMvc.perform(post("/article/like" + article.getArticleNo())).andExpect(status().isOk());
//    }

    private Article createArticle() throws Exception {
        Article article = Article.builder()
                .title("Title test")
                .content("Content test")
                .member(createMember())
                .build();

        return article;
    }

    private Member createMember() throws Exception {
        Member member =  Member.builder()
                .nickname("닉네임")
                .accountNo("test id")
                .build();

        return member;
    }



}
