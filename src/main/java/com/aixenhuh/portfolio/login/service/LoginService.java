package com.aixenhuh.portfolio.login.service;

import com.aixenhuh.portfolio.common.security.AuthToken;
import com.aixenhuh.portfolio.common.security.JwtAuthTokenProvider;
import com.aixenhuh.portfolio.exception.CustomAuthenticationException;
import com.aixenhuh.portfolio.exception.CustomJwtRuntimeException;
import com.aixenhuh.portfolio.user.entity.User;
import com.aixenhuh.portfolio.user.UserDTO;
import com.aixenhuh.portfolio.user.repository.UserRepository;
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
    UserRepository userRepository;

    @Override
    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if(user.isPresent()) {
            if(user.get().getPassword().equals(password)) {
                return user;
            } else {
                throw new CustomAuthenticationException();
            }
        } else {
            throw new CustomJwtRuntimeException();
        }
    }

    public String signIn(UserDTO userDto) {
        User save = userRepository.save(userDto.toEntity());
        return save.getId();
    }

    @Override
    public AuthToken createAuthToken(User userDTO) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return jwtAuthTokenProvider.createAuthToken(userDTO.getId(), userDTO.getRole().toString(), expiredDate);
    }
}
