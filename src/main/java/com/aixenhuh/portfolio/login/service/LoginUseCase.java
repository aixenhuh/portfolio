package com.aixenhuh.portfolio.login.service;

import com.aixenhuh.portfolio.core.security.AuthToken;
import com.aixenhuh.portfolio.core.user.User;

import java.util.Optional;
public interface LoginUseCase
{
    Optional<User> login(String email, String password);
    AuthToken createAuthToken(User userDTO);
}
