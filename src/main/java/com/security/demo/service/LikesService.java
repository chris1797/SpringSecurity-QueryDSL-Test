package com.security.demo.service;

import com.security.demo.entity.Member;

public interface LikesService {

    boolean addLike(Member member, Long article_id);
}
