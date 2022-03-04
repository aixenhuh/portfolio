package com.aixenhuh.portfolio.user;

import com.aixenhuh.portfolio.common.response.CommonResponse;
import com.aixenhuh.portfolio.exception.CustomAuthenticationException;
import com.aixenhuh.portfolio.user.entity.User;
import com.aixenhuh.portfolio.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<CommonResponse> getUsers() {
        Optional<List<User>> all = userService.findAll();
        CommonResponse ok = CommonResponse.builder().code("200").message("OK").status(200).data(all.get()).build();
        if(all.isPresent()) {
            return new ResponseEntity<>(ok, HttpStatus.OK);
        } else {
            throw new CustomAuthenticationException();
        }
    }
}
