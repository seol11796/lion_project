package com.example.lionPjt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll()
                .and()
                    .csrf().ignoringRequestMatchers(
                        new AntPathRequestMatcher("/h2-console/**"))
                .and()
                    .headers()
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
        ;

        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티 인증을 담당. 빈 생성 시 UserSecurityService와 PasswordEncoder가 자동 설정됨.
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        {
            return authenticationConfiguration.getAuthenticationManager();
        }
    }

}