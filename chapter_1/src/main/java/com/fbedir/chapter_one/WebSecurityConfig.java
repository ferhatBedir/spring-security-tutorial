package com.fbedir.chapter_one;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var adminUser = User.withDefaultPasswordEncoder().username("admin").password("admin").authorities("admin").build();
        var standardUser = User.withDefaultPasswordEncoder().username("user").password("123456").authorities("read").build();

        return new InMemoryUserDetailsManager(adminUser, standardUser);
    }

}
