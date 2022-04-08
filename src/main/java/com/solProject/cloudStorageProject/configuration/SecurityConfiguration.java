package com.solProject.cloudStorageProject.configuration;

import com.solProject.cloudStorageProject.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * configure(AuthenticationManagerBuilder auth):
 * used to tell Spring to use our AuthenticationService to check user logins
 * configure(HttpSecurity http):
 * used to configure the HttpSecurity object by chaining methods to express security requirements
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private AuthenticationService authenticationService;

    public SecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(this.authenticationService);
    }

    /**
     Allows all users to access the /signup page, as well as the css and js files.
     Allows authenticated users to make any request that's not explicitly covered elsewhere.
     Generates a login form at /login and allows anyone to access it.
     Redirects successful logins to the /home page.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true");

        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.logout()
                .logoutSuccessUrl("/login");
    }

}
