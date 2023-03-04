package com.zaritalk.demo.service;

import com.zaritalk.demo.entity.Member;

public interface LikesService {

    boolean addLike(Member member, Long article_id);
}
