package com.security.demo.memberTest;

import com.security.demo.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
public class MemberTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

//    @Test
//    @DisplayName("Authorization 테스트")
//    void getBoardWriteForm() throws Exception {
//
//        mockMvc.perform(get("/board/write")).andExpect(status().isOk());
//    }
}
