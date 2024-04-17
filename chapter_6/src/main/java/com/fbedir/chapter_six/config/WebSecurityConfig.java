package com.fbedir.chapter_six.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

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
                               .authorizeHttpRequests(r -> r.requestMatchers("/for-admin-role").hasRole("ADMIN")
                                                            .requestMatchers("/for-user-role").hasRole("USER")
                                                            .requestMatchers("/for-all-role").hasAnyRole("ADMIN", "USER")
                                                            .requestMatchers("/for-view-authorities").hasAuthority("VIEWDATA")
                                                            .requestMatchers("/for-write-authorities").hasAuthority("WRITEDATA")
                                                            .requestMatchers("/for-all-authorities").hasAnyAuthority("WRITEDATA","VIEWDATA"))
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
