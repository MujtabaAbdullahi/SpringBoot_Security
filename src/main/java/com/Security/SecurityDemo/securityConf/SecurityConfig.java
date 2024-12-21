package com.Security.SecurityDemo.securityConf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// this annotation is used to tell the spring boot that this class the configuration class
@Configuration
// this annotation is used to be able to use the 
@EnableWebSecurity
public class SecurityConfig {
  
    @Bean
      SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request) -> request.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();
      }
}
