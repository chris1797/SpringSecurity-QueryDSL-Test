package com.security.demo.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
            web.debug(true)
                    .ignoring()
                    .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**");// debug 모드 설정
//                .ignoring()
//                    .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**"); // 예시
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {


        // token 기반 방식이므로 csrf 설정 disable
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 해당 Role을 가진, 인증된 사용자만 허용
        httpSecurity.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        .requestMatchers("/login", "/signup").permitAll()
                        .requestMatchers("/article/write", "/article/edit", "/article/delete")
                        .hasAnyRole("ADMIN", "OWNER", "MEMBER")
                        .anyRequest()
                        .authenticated());

        // login 설정
        httpSecurity.formLogin((formLogin) -> formLogin
                .loginPage("/login").defaultSuccessUrl("/", true)    // GET 요청 (login form을 보여줌)
                .loginProcessingUrl("/auth") .defaultSuccessUrl("/", true)   // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("id")	// login에 필요한 id 값 (default는 username)
                .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
                .defaultSuccessUrl("/"));	// login에 성공하면 /로 redirect

        // logout 설정
        httpSecurity.logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"));	// logout에 성공하면 /로 redirect

        return httpSecurity.build();
    }

}
