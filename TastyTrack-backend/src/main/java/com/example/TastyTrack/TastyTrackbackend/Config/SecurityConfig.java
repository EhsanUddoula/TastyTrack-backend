package com.example.TastyTrack.TastyTrackbackend.Config;

import com.example.TastyTrack.TastyTrackbackend.Service.Impl.CustomRestDetService;
import com.example.TastyTrack.TastyTrackbackend.Service.Impl.CustomUserDetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.DaoSupport;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService restUserDetailsService() {
        return new CustomRestDetService();
    }

    @Bean
    public UserDetailsService userUserDetailsService() {
        return new CustomUserDetService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder= http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userUserDetailsService()).passwordEncoder(passwordEncoder());
        authenticationManagerBuilder
                .userDetailsService(restUserDetailsService()).passwordEncoder(passwordEncoder());

        http.csrf(c -> c.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/api/restaurant/register",
                        "api/restaurant/login",
                        "/api/food/**",
                        "/api/user/register",
                        "/api/order/**",
                        "/api/review/**").permitAll().requestMatchers("/api/restaurant/**").hasAuthority("RESTAURANT")
                        .requestMatchers("/api/user/**").hasAuthority("USER"))
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());

        return http.build();
    }

}
