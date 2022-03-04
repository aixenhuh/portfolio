package com.aixenhuh.portfolio.user.entity;

import com.aixenhuh.portfolio.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Role role;
}
