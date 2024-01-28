package com.jesusfernandez.superheroapi.configuration;

import com.jesusfernandez.superheroapi.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(@Autowired PasswordEncoder passwordEncoder,
                                                         @Value("${security.admin.name}") String adminName,
                                                         @Value("${security.admin.password}") String adminPassword,
                                                         @Value("${security.user.name}") String userName,
                                                         @Value("${security.user.password}") String userPassword) {
        UserDetails userDetails = User.withUsername(userName)
                .password(passwordEncoder.encode(userPassword))
                .roles(Roles.USER)
                .build();

        UserDetails adminDetails = User.withUsername(adminName)
                .password(passwordEncoder.encode(adminPassword))
                .roles(Roles.ADMIN)
                .build();
        return new InMemoryUserDetailsManager(userDetails, adminDetails);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
