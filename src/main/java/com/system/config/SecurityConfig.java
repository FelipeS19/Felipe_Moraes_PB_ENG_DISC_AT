package com.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       http
    .csrf(csrf -> csrf.disable())

    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/login").permitAll()

        .requestMatchers("/products").permitAll()

        .requestMatchers("/products/**").hasAnyRole("USER", "ADMIN")

        .requestMatchers("/users/**").hasRole("ADMIN")

        .anyRequest().authenticated()
    )

    .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/products", true)
        .permitAll()
    )

    .logout(logout -> logout.permitAll());

        return http.build();
    }
}