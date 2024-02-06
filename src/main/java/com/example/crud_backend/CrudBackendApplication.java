package com.example.crud_backend;

import com.example.crud_backend.security.config.RsaKeysConfig;
import com.example.crud_backend.security.service.RoleServices;
import com.example.crud_backend.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)

public class CrudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudBackendApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(UserService userService, RoleServices roleServices ) {
        return args -> {
            userService.addRoleToUser("USER", "mohamed");
        };
    }
}
