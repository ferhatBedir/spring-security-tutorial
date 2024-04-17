package com.fbedir.chapter_seven.config;

import com.fbedir.chapter_seven.filter.CustomAfterFilter;
import com.fbedir.chapter_seven.filter.CustomBeforeFilter;
import com.fbedir.chapter_seven.filter.CustomFilterOne;
import com.fbedir.chapter_seven.filter.CustomFilterTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //@formatter:off
        var httpSecurity = http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                               .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                                   CorsConfiguration config = new CorsConfiguration();
                                   config.setAllowedOrigins(Collections.singletonList("*"));
                                   config.setAllowedMethods(Collections.singletonList("*"));
                                   config.setAllowCredentials(true);
                                   config.setAllowedHeaders(Collections.singletonList("*"));
                                   config.setMaxAge(3600L);
                                   return config;

                               }))
                               .addFilterBefore(new CustomBeforeFilter(), BasicAuthenticationFilter.class)
                               .addFilterAfter(new CustomAfterFilter(), BasicAuthenticationFilter.class)
                               .addFilterAt(new CustomFilterOne(),BasicAuthenticationFilter.class)
                               .addFilterAt(new CustomFilterTwo(),BasicAuthenticationFilter.class)
                               .csrf(AbstractHttpConfigurer::disable)
                               .authorizeHttpRequests(r -> r.anyRequest().authenticated())
                               .formLogin(AbstractHttpConfigurer::disable)
                               .httpBasic(c -> c.init(http));


        //@formatter:on
        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
