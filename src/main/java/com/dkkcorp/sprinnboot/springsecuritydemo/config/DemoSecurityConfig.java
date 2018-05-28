package com.dkkcorp.sprinnboot.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder=User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("john").password("test").roles("EMP0"))
                .withUser(userBuilder.username("mary").password("test").roles("EMP0","MAN"))
                .withUser(userBuilder.username("dav").password("test").roles("EMP0","ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMP0")
                .antMatchers("/manager/**").hasRole("MAN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/common/**").hasAnyRole("MAN","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .permitAll()
        .and()
        .logout().permitAll();
    }
}
