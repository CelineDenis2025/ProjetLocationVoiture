package com.accenture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                ).permitAll()
//                        .requestMatchers("/utilisateurs/**").permitAll()
//                        .requestMatchers("/taches/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/taches/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/taches/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.PUT, "/taches/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.PATCH, "/taches/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.DELETE, "/taches/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("select email, password, 1  from connected_user where email = ?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//                "select cu.email,coalesce(a.role, c.role) as authority from connected_user cu left join admin a on cu.id = a.id left join customer c on cu.id = c.id where cu.email = ?"
//        );
//        return jdbcUserDetailsManager;
//    }


    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                """
                SELECT cu.email, cu.password, true
                FROM connected_user cu
                LEFT JOIN admin a ON cu.id = a.id
                LEFT JOIN customer c ON cu.id = c.id
                WHERE cu.email = ?
                """
        );


        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                """
                SELECT cu.email,
                       CASE
                           WHEN a.role IS NOT NULL THEN 'ROLE_ADMIN'
                           WHEN c.role IS NOT NULL THEN 'ROLE_CUSTOMER'
                       END AS authority
                FROM connected_user cu
                LEFT JOIN admin a ON cu.id = a.id
                LEFT JOIN customer c ON cu.id = c.id
                WHERE cu.email = ?
                """
        );

        return jdbcUserDetailsManager;
    }


//    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
