package com.fbedir.chapter_five.config;


import com.fbedir.chapter_five.entity.CustomerEntity;
import com.fbedir.chapter_five.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomerEntity customerEntity = customerRepository.findByEmail(userName).orElseThrow(() -> new BadCredentialsException("User not found.."));
        if (passwordEncoder.matches(password, customerEntity.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userName, password, null);
        }
        throw new BadCredentialsException("Invalid password..");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
