package com.aixenhuh.portfolio.core.user.repository;

import com.aixenhuh.portfolio.core.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, String> {
}
