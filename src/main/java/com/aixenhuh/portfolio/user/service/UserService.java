package com.aixenhuh.portfolio.user.service;

import com.aixenhuh.portfolio.user.entity.User;
import com.aixenhuh.portfolio.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<List<User>> findAll() {
        Optional<List<User>> all = Optional.of(userRepository.findAll());
        return all;
    }
}
