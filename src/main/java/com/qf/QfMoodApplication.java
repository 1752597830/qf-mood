package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: sin
 * @Date: 2023/5/20 - 05 - 20 - 21:41
 * @Description: com.qf
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.qf.mapper")
public class QfMoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(QfMoodApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
