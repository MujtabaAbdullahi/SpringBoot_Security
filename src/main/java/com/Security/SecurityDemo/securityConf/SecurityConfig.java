package com.Security.SecurityDemo.securityConf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// this annotation is used to tell the spring boot that this class the configuration class
@Configuration
// this annotation is used to be able to use the 
@EnableWebSecurity    
@EnableMethodSecurity
public class SecurityConfig {
  
    @Bean
      SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        //this line of code is used to allow any specific url
         http.authorizeHttpRequests((request) ->
          request.requestMatchers("/h2-console/**").permitAll()
          .anyRequest().authenticated());
       // http.authorizeHttpRequests((request) -> request.anyRequest().authenticated());
        // this line of code is used to make our http requset stateless request
        http.sessionManagement(session -> 
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // this line of code is used to enable the form login method
        //http.formLogin(withDefaults());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        http.httpBasic(withDefaults());
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        // or
        // http.csrf(csrf -> csrf.disable());



        return http.build();
      }

      @Bean
      public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withUsername("user").
        password("{noop}user123")
        .roles("USER").
        build();

        UserDetails admin = User.withUsername("admin").
        password("{noop}admin123")
        .roles("ADMIN").
        build();

        return new InMemoryUserDetailsManager(user1, admin);
      }
}
