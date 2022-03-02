package com.aixenhuh.portfolio.login;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class LoginRequestDTO {
    private String id;
    private String password;
}
