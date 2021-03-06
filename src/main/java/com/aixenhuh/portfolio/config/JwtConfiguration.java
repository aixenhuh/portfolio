package com.aixenhuh.portfolio.config;

import com.aixenhuh.portfolio.common.security.JwtAuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtAuthTokenProvider jwtProvider() {
        return new JwtAuthTokenProvider(secret);
    }
}
