package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisHttpSession  extends WebSecurityConfigurerAdapter{


    /**
     * white urls for static resources
     */
    String[] whiteUrls = {"/webjars/**", "/static/**", "/static/css/**", "/static/images/**", "/static/js/**", "/swagger-ui.html", "/v2/api-docs", "/configuration/ui", "/swagger-resources/configuration/ui", "/swagger-resources/**",
            "/configuration/security", "/swagger-resources/configuration/ui", "**/*.css", "/webjars/**", "**/*.js", "**/*.map", "*.html", "/health", "/metrics", "/robots.txt", "/error", "/actuator/**", "/favicon.ico"};


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http

                .formLogin().loginPage("/loginPage").permitAll().loginProcessingUrl("/doLogin").permitAll().successForwardUrl("/home")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/doLogin","/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }


    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(whiteUrls);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }


}
