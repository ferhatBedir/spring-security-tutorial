package com.fbedir.chapter_five.config;

import com.fbedir.chapter_five.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        var httpSecurity = http.securityContext((context) -> context.requireExplicitSave(false))
                               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                               .cors(cors -> cors.configurationSource(request -> {
                                   CorsConfiguration config = new CorsConfiguration();
                                   config.setAllowedOrigins(List.of("*"));
                                   config.setAllowedMethods(List.of("*"));
                                   config.setAllowCredentials(true);
                                   config.setAllowedHeaders(List.of("*"));
                                   config.setMaxAge(3600L);
                                   return config;
                               }))
                               .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                                                   .ignoringRequestMatchers("/ignore-csrf")
                                                   .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                               .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                               .authorizeHttpRequests(r -> r.anyRequest().authenticated())
                               .formLogin(Customizer.withDefaults())
                               .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
