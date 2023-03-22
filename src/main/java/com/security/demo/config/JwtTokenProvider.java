package com.security.demo.config;

import com.security.demo.domain.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Getter
@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final Long validityInMilliseconds;
    private final String token;



    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds,
                            Member member) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
        this.token = createToken(member);
    }

    //토큰생성 (username, secretKey 사용)
    private String createToken(String userName, String secretKey, Long expiredMs) {
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

    //토큰생성 (인증된 member 인스턴스를 받아 토큰 생성)
    private String createToken(Member member) {
        // ex) LESSOR 1
        return member.getAccount_type() + " " + member.getMember_idx();
    }

    //토큰에서 값 추출
    private String getSubject(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 토큰 사용 용도 ex) 파라미터 "user"
    }

    // 토큰 검증
    private boolean validateToken(String token) {
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
