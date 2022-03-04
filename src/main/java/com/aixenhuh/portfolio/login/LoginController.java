package com.aixenhuh.portfolio.login;

import com.aixenhuh.portfolio.common.response.CommonResponse;
import com.aixenhuh.portfolio.common.security.JwtAuthToken;
import com.aixenhuh.portfolio.exception.LoginFailedException;
import com.aixenhuh.portfolio.login.service.LoginService;
import com.aixenhuh.portfolio.user.entity.User;
import com.aixenhuh.portfolio.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(path="/getLogin")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<User> optionalUserDTO = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if(optionalUserDTO.isPresent()) {

            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(optionalUserDTO.get());
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            CommonResponse login_success = CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();

            return new ResponseEntity<>(login_success, headers, HttpStatus.OK);
        }
        else {
            throw new LoginFailedException();
        }
    }

    @PostMapping(path="/singIn")
    public ResponseEntity<CommonResponse> signIn(@RequestBody UserDTO userDto) {
        String s = loginService.signIn(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
