package com.dotcipher.easybank.config;

import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration()
public class EasyBankSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorize ->
                        authorize
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact").permitAll()
                )
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
    }

}

/*
authorizeRequests ->
                authorizeRequests
                        .antMatchers("/myLoans").hasRole("USER")
                        .antMatchers("/myCards").hasRole("USER")
                        .antMatchers("/notices").permitAll()
                        .antMatchers("/balance").permitAll()
                        .anyRequest().authenticated()
 */