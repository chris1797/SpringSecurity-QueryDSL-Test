package com.security.demo.service;

import com.security.demo.domain.Member;

public interface LikesService {

    boolean addLike(Member member, Long article_idx);
}
