package com.aixenhuh.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.sound.sampled.Port;

@SpringBootApplication
public class PortfolioApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //SpringApplication.run(PortfolioApplication.class, args);
        SpringApplication app = new SpringApplication(PortfolioApplication.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortfolioApplication.class);
    }
}
