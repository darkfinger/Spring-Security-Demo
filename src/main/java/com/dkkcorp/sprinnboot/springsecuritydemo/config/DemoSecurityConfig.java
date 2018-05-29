package com.dkkcorp.sprinnboot.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DemoSecurityConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //jdbc authentication

        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enable from user where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from user u inner join user_role ur on(u.id=ur.user_id) inner join role r on(ur.role_id=r.id) where u.username=?\n")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

        //in memory login
//        User.UserBuilder userBuilder=User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("john").password("test").roles("EMP0"))
//                .withUser(userBuilder.username("mary").password("test").roles("EMP0","MAN"))
//                .withUser(userBuilder.username("dav").password("test").roles("EMP0","ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPO")
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
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/denied")
        ;
    }
}
