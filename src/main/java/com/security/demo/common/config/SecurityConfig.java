package com.security.demo.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        /**
         * WebSecurityCustomizer : WebSecurity 설정을 커스터마이징하기 위한 인터페이스
         * debug() : debug 모드 설정
         * ignoring() : WebSecurity 설정을 무시하고 특정 요청을 무시하도록 설정
         */
        return (web) -> {
            web.debug(true) // debug 모드 설정
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**"); // 예시
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        /**
         * headers() : HTTP 응답 Header 설정
         * xssProtection() : XSS(Cross Site Scripting) 공격 방어
         * and() : 이전까지 config 구성을 끝내고 다른 설정으로 넘어감
         * contentSecurityPolicy() : Content Security Policy (CSP) 설정, script-src 'self' : 자신의 도메인에서만 script 실행 허용
         */
        httpSecurity.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");

        // token 기반 방식이므로 csrf 설정 disable
        httpSecurity.csrf().disable();

        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 해당 Role을 가진, 인증된 사용자만 허용
        httpSecurity.authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/article/write", "/article/edit", "/article/delete")
                    .hasAnyRole("REALTOR", "LESSOR", "LESSEE")
                .anyRequest().authenticated();

        // login 설정
        httpSecurity.formLogin()
                .loginPage("/login").defaultSuccessUrl("/", true)    // GET 요청 (login form을 보여줌)
                .loginProcessingUrl("/auth") .defaultSuccessUrl("/", true)   // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("memberid")	// login에 필요한 id 값 (default는 username)
                .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
                .defaultSuccessUrl("/");	// login에 성공하면 /로 redirect

        // logout 설정
        httpSecurity.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");	// logout에 성공하면 /로 redirect

        return httpSecurity.build();
    }

//    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
