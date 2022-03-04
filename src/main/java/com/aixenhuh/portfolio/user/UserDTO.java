package com.aixenhuh.portfolio.user;


import com.aixenhuh.portfolio.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String password;
    private Role role;

    public User toEntity() {
        return User.builder().email(email)
                .name(name)
                .password(password)
                .role(role).build();
    }
}
