package com.fbedir.chapter_eight.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.fbedir.chapter_eight.util.JWTConstant.AUTHORIZATION_HEADER;
import static com.fbedir.chapter_eight.util.JWTConstant.JWT_KEY;
import static java.util.Objects.nonNull;

public class JWTGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (nonNull(authentication)) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                             .issuer("demo-project")
                             .subject("jwt-demo")
                             .claim("username", authentication.getName())
                             .issuedAt(new Date())
                             .expiration(new Date(new Date().getTime() + 30000000)) //30000000ms
                             .signWith(key)
                             .compact();
            response.setHeader(AUTHORIZATION_HEADER, jwt);
        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/sign-in");
    }

}
