package com.security.demo.config;

import com.security.demo.entity.Member;
import com.security.demo.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final MemberRepository memberRepository;


    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds, MemberRepository memberRepository) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.memberRepository = memberRepository;
    }

    //토큰생성 (username, secretKey 사용)
    public String createToken(String userName, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 토큰 만료시간 설정
//              .signWith(SignatureAlgorithm.HS256, secretKey) // deprecated
                .signWith(key, SignatureAlgorithm.HS256)
                // key를 받아 HS256(서명 알고리즘)으로 서명한다는 의미 (Apple은 RS256 알고리즘으로 서명해야 한다.)
                .compact();
    }

    //토큰생성 (LoginRequest 사용)
    public String createToken(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberid(loginRequest.getMemberid())
                .orElseThrow(() -> new NullPointerException("해당되는 유저가 없습니다."));

        return member.getAccount_type() + " " + member.getMember_idx(); // ex) LESSOR 1
    }

    //토큰에서 값 추출
    public String getSubject(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 토큰 사용 용도 ex) 파라미터 "user"
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // 파싱 및 검증, 실패 시 에러

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
