package com.my.zelkova_back.security;

import com.my.zelkova_back.auth.token.JwtUtil;
import com.my.zelkova_back.security.filter.JwtAuthenticationFilter;
import com.my.zelkova_back.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	public SecurityConfig(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(
					"/v3/api-docs/**",
					"/swagger-ui.html",
					"/swagger-ui/**",
					"/api-docs/**"
				).permitAll()
				.anyRequest().authenticated() // 여기만 살짝 바꿈
			)
			.formLogin(form -> form
				.loginPage("/login")
				.permitAll())
			.logout(logout -> logout
				.logoutSuccessUrl("/"))

			// ✅ JWT 필터 추가
			.addFilterBefore(
				new JwtAuthenticationFilter(jwtUtil, userDetailsService),
				UsernamePasswordAuthenticationFilter.class
			);

		return http.build();
	}
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(); // 비밀번호 암호화
        }
}
