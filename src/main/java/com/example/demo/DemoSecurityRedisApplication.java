package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableRedisHttpSession
@Controller
public class DemoSecurityRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityRedisApplication.class, args);
	}
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@PostMapping("/home")
    @ResponseBody
	public String getUser(@AuthenticationPrincipal UserDetails user) {
		return new StringBuilder("Hello, ").append(user.getUsername()).append("!").toString();
	}

    @RequestMapping(path = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        authRequest.setDetails(userDetails);
//        authenticationManager.authenticate(authRequest);
//            authenticationManager.authenticate(authRequest);
        return "redirect:/home";

    }
	
}
