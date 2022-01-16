package com.toto.bookshop;

import com.toto.bookshop.services.UserDetailsServiceImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                //.antMatchers("/home", "/register").permitAll()
                //.anyRequest().authenticated()
                .antMatchers("/login").authenticated()
                .antMatchers("/addbook").hasRole("PUBLISHER")
                .antMatchers("/editbook").hasRole("PUBLISHER")
                .antMatchers("/mybooks").hasRole("PUBLISHER")
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/books")
                .and()
            .logout()
                .logoutSuccessUrl("/books")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }
    
}