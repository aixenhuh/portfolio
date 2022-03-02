package com.aixenhuh.portfolio.core.user.service;

import com.aixenhuh.portfolio.core.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<List<User>> findAll();
}
