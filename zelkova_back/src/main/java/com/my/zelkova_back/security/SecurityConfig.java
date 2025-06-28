package com.my.zelkova_back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF는 테스트할 때 불편해서 비활성화
                .csrf(csrf -> csrf.disable())

                // 모든 요청 다 허용, 필터 설정하려면 여기 고치기
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())

                // 로그인 페이지는 /login, 누구나 들어올 수 있음
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())

                // 로그아웃 성공하면 메인(/)으로 이동
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));

        // 설정 끝나면 필터 체인 만들어서 리턴(검사 목록 넘겨주기)
        return http.build();
    }
}