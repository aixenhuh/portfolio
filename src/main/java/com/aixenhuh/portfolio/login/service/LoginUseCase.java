package com.aixenhuh.portfolio.login.service;

import com.aixenhuh.portfolio.common.security.AuthToken;
import com.aixenhuh.portfolio.user.entity.User;

import java.util.Optional;
public interface LoginUseCase
{
    Optional<User> login(String email, String password);
    AuthToken createAuthToken(User userDTO);
}
