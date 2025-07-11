package com.ferhatsertkaya.require4testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // scanBasePackages nicht nötig, da in com.ferhatsertkaya.require4testing
public class Require4TestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(Require4TestingApplication.class, args);
    }
}