package com.security.demo.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http.authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/article/write", "/article/edit", "/article/delete")
                    .hasAnyRole("REALTOR", "LESSOR", "LESSEE")
                .anyRequest().authenticated();

        // login 설정
        http.formLogin()
                .loginPage("/login").defaultSuccessUrl("/", true)    // GET 요청 (login form을 보여줌)
                .loginProcessingUrl("/auth") .defaultSuccessUrl("/", true)   // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("memberid")	// login에 필요한 id 값 (default는 username)
                .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
                .defaultSuccessUrl("/");	// login에 성공하면 /로 redirect

        // logout 설정
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");	// logout에 성공하면 /로 redirect

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
