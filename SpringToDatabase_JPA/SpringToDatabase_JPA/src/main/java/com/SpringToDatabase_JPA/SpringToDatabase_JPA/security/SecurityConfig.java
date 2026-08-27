package com.SpringToDatabase_JPA.SpringToDatabase_JPA.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                                .requestMatchers("/api/v1/users/*/orders/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated());
// this is for basic user and password while not JWT       .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

    }

// this is usse in memory not database  BEFOR USE JWT
//    @Bean
//    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
//        UserDetails admin = User.withUsername("Admin")
//                .roles("ADMIN")
//                .password(passwordEncoder.encode("pass123"))
//                .build();
//
//
//        UserDetails user = User.withUsername("Atul")
//                .roles("USER")
//                .password(passwordEncoder.encode("user123"))
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//
//
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig){
        return authconfig.getAuthenticationManager();
    }





}
