package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.jk.mapper")
@SpringBootApplication
public class AntServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntServiceApplication.class, args);
    }

}
