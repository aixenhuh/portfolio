package com.aixenhuh.portfolio.login.service;

import com.aixenhuh.portfolio.core.security.AuthToken;
import com.aixenhuh.portfolio.core.security.JwtAuthTokenProvider;
import com.aixenhuh.portfolio.core.user.Role;
import com.aixenhuh.portfolio.core.user.User;
import com.aixenhuh.portfolio.core.user.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final static long LOGIN_RETENTION_MINUTES = 30;

    @Autowired
    UserRedisRepository userRedisRepository;

    @Override
    public Optional<User> login(String id, String password) {

        User userDTO = User.builder()
                .id(id)
                .role(Role.ROLE_USER)
                .password(password)
                .build();

        userRedisRepository.save(userDTO);

        Optional<User> byId = userRedisRepository.findById(userDTO.getId());
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        System.out.println(byId.get().getId());
        System.out.println("bbbbbbbbbbbbbbbbbbb");

        return Optional.ofNullable(userDTO);
    }

    @Override
    public AuthToken createAuthToken(User userDTO) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return jwtAuthTokenProvider.createAuthToken(userDTO.getId(), userDTO.getRole().toString(), expiredDate);
    }
}
