package com.mritun.springsecurity.configration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) throws Exception {
        UserDetails userDetails = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123")).roles("ADMIN").build();

        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(userDetails);
        userDetailsManager.createUser(User.withUsername("ibm").password(passwordEncoder.encode("ibm")).roles("USER").build());
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
       .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/api/v1/query").authenticated()
                                        .requestMatchers("/api/v1/admin").hasAnyRole("ADMIN"))
                .requiresChannel(channel -> channel.requestMatchers("/**").requiresSecure())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
