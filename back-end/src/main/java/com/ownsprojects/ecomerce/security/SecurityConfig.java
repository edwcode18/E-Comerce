package com.ownsprojects.ecomerce.security;

import com.ownsprojects.ecomerce.security.filters.JwtAuthenticationFilter;
import com.ownsprojects.ecomerce.security.filters.JwtAuthorizationFilter;
import com.ownsprojects.ecomerce.security.jwt.JwtUtils;
import com.ownsprojects.ecomerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up security in the application.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomerService customerService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    /**
     * Defines the security filter chain for the application.
     *
     * @param httpSecurity The HttpSecurity object to configure security settings.
     * @param authenticationManager The AuthenticationManager for handling authentication.
     * @return A SecurityFilterChain instance for configuring security filters.
     * @throws Exception If there is an exception while configuring security.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers(AUTH_WHITELIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Provides a PasswordEncoder bean to handle password encoding and verification.
     *
     * @return A BCryptPasswordEncoder instance for password encoding.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines an AuthenticationManager bean for handling user authentication.
     *
     * @param httpSecurity The HttpSecurity object to configure authentication settings.
     * @param passwordEncoder The PasswordEncoder for encoding and verifying passwords.
     * @return An AuthenticationManager for user authentication.
     * @throws Exception If there is an exception while configuring authentication.
     */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customerService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v2/api-docs/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/configuration/**",
    };
}