package com.cpujazz.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cpujazz.front", "com.cpujazz.common"})
public class MelodyShareFrontSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(MelodyShareFrontSpringApplication.class, args);
    }
}
