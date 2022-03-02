package com.aixenhuh.portfolio.login;

import com.aixenhuh.portfolio.core.response.CommonResponse;
import com.aixenhuh.portfolio.core.security.JwtAuthToken;
import com.aixenhuh.portfolio.core.user.User;
import com.aixenhuh.portfolio.exception.LoginFailedException;
import com.aixenhuh.portfolio.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(path="/getLogin")
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<User> optionalUserDTO = loginService.login(loginRequestDTO.getId(), loginRequestDTO.getPassword());

        if(optionalUserDTO.isPresent()) {

            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(optionalUserDTO.get());

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();
        }
        else {
            throw new LoginFailedException();
        }
    }

}
