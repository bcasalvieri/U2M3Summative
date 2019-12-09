package com.company.BradCasalvieriU2M3Summative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.PUT, "/game/*").hasAuthority("STAFF")
                .mvcMatchers(HttpMethod.PUT, "/console/*").hasAuthority("STAFF")
                .mvcMatchers(HttpMethod.PUT, "/tshirt/*").hasAuthority("STAFF")
                .mvcMatchers(HttpMethod.POST, "/games").hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.POST, "/consoles").hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.POST, "/tshirts").hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.DELETE, "/game/*").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/console/*").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/tshirt/*").hasAuthority("ADMIN")
                .anyRequest().permitAll();

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
