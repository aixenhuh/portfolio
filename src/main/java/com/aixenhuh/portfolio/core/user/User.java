package com.aixenhuh.portfolio.core.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RedisHash(value="user", timeToLive = 30)
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private Role role;

    public User(String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
