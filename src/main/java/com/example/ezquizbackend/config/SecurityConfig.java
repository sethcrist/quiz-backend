package com.example.ezquizbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security settings for the application.
 * Defines HTTP security configurations to handle CSRF protection, request authorization,
 * form login and logout functionalities.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the security filter chain that applies to HTTP requests.
     *
     * @param http the {@link HttpSecurity} to configure
     * @return the {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disables CSRF protection for demonstration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/public/**").permitAll() // public endpoints dont require auth
                        .requestMatchers("/api/v1/quiz/**").authenticated() // quiz related endpoints require auth
                        .requestMatchers("/api/v1/answers/**").authenticated() // answer related endpoints require auth
                        .anyRequest().authenticated() // all other request must be auth
                )
                .formLogin(form -> form
                        .loginPage("/login") // custom login page
                        .permitAll() // lets everyone see the login page without auth
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // redirects to login when logged out
                        .deleteCookies("JSESSIONID") // deletes JSESSIONID cookie on logout
                        .clearAuthentication(true) // clears auth attributes
                        .permitAll() // lets users access the logout page
                );
        return http.build();
    }

    /**
     * Configures security to ignore certain request patterns.
     * Can help with performance by bypassing security checks for static APIs.
     *
     * @return the {@link WebSecurityCustomizer}
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/public/**"); // Ignores security filter for the specified path.
    }

}
