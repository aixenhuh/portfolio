package com.aixenhuh.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "welshcorgi";
    }

    @GetMapping("/welsh")
    public String welsh() {
        return "welsh bab jo";
    }
}
