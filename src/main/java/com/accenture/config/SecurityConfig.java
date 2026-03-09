package com.accenture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/customers").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admins").permitAll()

                        // Example protected endpoints:
                        // .requestMatchers("/admins/**").hasRole("ADMIN")
                        // .requestMatchers("/cutomers/**").hasRole("USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);

        // Must return: username, password, enabled
        jdbc.setUsersByUsernameQuery(
                """
                        SELECT cu.email, cu.password, true
                        FROM connected_user cu
                        WHERE cu.email = ?
                        """
        );

        // Must return: username, authority
        // IMPORTANT: prefix with ROLE_ and provide a default when neither admin nor customer
        jdbc.setAuthoritiesByUsernameQuery(
                """
                        SELECT cu.email,
                               COALESCE(
                                 CASE
                                   WHEN a.role IS NOT NULL THEN 'ROLE_ADMIN'
                                   WHEN c.role IS NOT NULL THEN 'ROLE_USER'
                                   ELSE 'ROLE_USER' -- default role if you want everyone to be at least USER
                                 END,
                                 'ROLE_USER'
                               ) AS authority
                        FROM connected_user cu
                        LEFT JOIN admin a    ON cu.id = a.id
                        LEFT JOIN customer c ON cu.id = c.id
                        WHERE cu.email = ?
                        """
        );

        return jdbc;
    }
}