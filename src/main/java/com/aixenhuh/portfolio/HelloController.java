package com.aixenhuh.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/wow")
    public String hello() {
        return "welshcorgi";
    }

    @GetMapping("/hello/welsh")
    public String welsh() {
        return "welsh bab jo";
    }


}
