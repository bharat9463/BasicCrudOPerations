package com.example.postgresql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("xadmin").password("password").roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("bharat").password("password1").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.csrf().disable();
       //these are disabled for testing purpose
        //http.authorizeRequests().antMatchers("/product/create").hasAnyRole("ADMIN","USER").and().oauth2Login();
        //http.authorizeRequests().antMatchers("/product/update/{id:[\\d+]}").hasAnyRole("USER","ADMIN").and().oauth2Login();
        //http.authorizeRequests().antMatchers("/product/delete/{id:[\\d+]}").hasRole("ADMIN").and().oauth2Login();

    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
