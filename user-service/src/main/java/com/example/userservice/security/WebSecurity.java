package com.example.userservice.security;

import com.example.userservice.enums.UserRole;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // @PreAuthorize, @PostAuthorize 어노테이션 활성화
@RequiredArgsConstructor
public class WebSecurity {

    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화
            .cors(Customizer.withDefaults())
//            .cors(AbstractHttpConfigurer::disable) // CORS 보호 비활성화 (개발 편의)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 비활성화

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/users/health-check").permitAll() // 인증 관련 경로는 모두 허용
                .requestMatchers("/h2-console/**").permitAll()  // 특정 경로 허용
                .requestMatchers("/**").access(
                    new WebExpressionAuthorizationManager(
                        "hasIpAddress('127.0.0.1') or hasIpAddress('::1') or " +
                            "hasIpAddress('10.90.26.89') or hasIpAddress('::1')")) // host pc ip address
                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name()) // Enum 이름 사용
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 기반이므로 세션 사용 안 함
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 등록
            .userDetailsService(userService) // 우리의 UserDetailsService 구현체 등록
            .httpBasic(Customizer.withDefaults())  // ← Basic 인증 추가
            .headers((headers) -> headers
                .frameOptions((frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }
}
