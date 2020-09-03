package com.mr.springsquashshop;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder user = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(user.username("admin").password("admin").roles("ADMIN"))
                .withUser(user.username("marek").password("marek").roles("MANAGER"))
                .withUser(user.username("ania").password("ania").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/categories/**").hasRole("MANAGER")
                .antMatchers("/api/products/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin();
    }
}
