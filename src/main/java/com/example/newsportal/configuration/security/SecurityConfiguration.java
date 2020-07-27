package com.example.newsportal.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProvider SecurityProvider;

    @Autowired
    public SecurityConfiguration(SecurityProvider SecurityProvider) {
        this.SecurityProvider = SecurityProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(SecurityProvider);
    }

        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .cors().disable();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
