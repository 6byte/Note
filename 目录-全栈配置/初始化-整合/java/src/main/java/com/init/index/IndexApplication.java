package com.init.index;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.init.index")
@MapperScan("com.init.index.module")
public class IndexApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class , args);
    }
}
