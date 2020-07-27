package com.example.newsportal.configuration.security;

import com.example.newsportal.model.User;
import com.example.newsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SecurityProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    @Autowired
    public SecurityProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            User user = userRepository.findByUserName(authentication.getName());
            if(user == null) {
                throw new UsernameNotFoundException(String.format("User not found"));
            }
            if(!user.getPassword().equals(authentication.getCredentials().toString())) {
                throw new BadCredentialsException("Incorrect username");
            }
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(it -> new SimpleGrantedAuthority("ROLE_" + it.getName())).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
