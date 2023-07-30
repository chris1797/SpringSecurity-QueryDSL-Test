package com.security.demo.memberTest;

import com.security.demo.common.role.Role;
import com.security.demo.domain.Article;
import com.security.demo.domain.Member;
import com.security.demo.repository.ArticleRepository;
import com.security.demo.service.ArticleService;
import com.security.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;


/**
 * @RunWith(SpringRunner.class) : JUnit4에서 SpringMvc - service 테스트를 하기 위해 선언
 */

@Slf4j
@SpringBootTest
//@DataJpaTest
@RunWith(SpringRunner.class)
public class MemberTest {

    @Autowired
    ArticleService articleService;

    @Mock
    ArticleRepository articleRepository;


    @Test
    @DisplayName("게시클 조회 테스트")
    public void findArticle() {
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList.get(0).getContent()).isEqualTo("test content");
    }
}
