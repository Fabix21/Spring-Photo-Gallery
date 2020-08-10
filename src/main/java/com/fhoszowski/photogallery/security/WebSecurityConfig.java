package com.fhoszowski.photogallery.security;

import com.fhoszowski.photogallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private UserService userService;

    @Autowired
    public WebSecurityConfig( AuthenticationSuccessHandler authenticationSuccessHandler ) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.httpBasic().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/login*").permitAll()
            .antMatchers("/addUser*").permitAll()
            .antMatchers("/addPhoto").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin().successHandler(authenticationSuccessHandler);
        http.headers().frameOptions().sameOrigin();
    }
}
