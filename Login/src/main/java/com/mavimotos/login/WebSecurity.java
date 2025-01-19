package com.mavimotos.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll().anyRequest().authenticated()).formLogin(login -> login
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    authentication.getAuthorities().forEach(authority -> {
                        try {
                            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                response.sendRedirect("/admin");
                            } else {
                                response.sendRedirect("/private");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                })
                .permitAll()
        ).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("edgar").password(passwordEncoder().encode("12345")).roles("USER").build()
                , User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}