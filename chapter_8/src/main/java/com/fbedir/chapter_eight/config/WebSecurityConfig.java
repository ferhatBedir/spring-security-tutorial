package com.fbedir.chapter_eight.config;

import com.fbedir.chapter_eight.filter.JWTGeneratorFilter;
import com.fbedir.chapter_eight.filter.JWTValidatorFilter;
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
                               .csrf(AbstractHttpConfigurer::disable)
                               .addFilterBefore(new JWTValidatorFilter(), BasicAuthenticationFilter.class)
                               .addFilterAfter(new JWTGeneratorFilter(), BasicAuthenticationFilter.class)
                               .authorizeHttpRequests(r -> r.requestMatchers("/sign-in", "/get-customer/*").authenticated()
                                                            .requestMatchers("/sign-up", "/public").permitAll())
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
