package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * 验证码认证 -> 可改用 Redis 实现。
 */
class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    public LoginAuthenticationFilter(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService=userDetailsService;
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
     HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        authRequest.setDetails(userDetails);
        return authenticationManager.authenticate(authRequest);

    }

}
