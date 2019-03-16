package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/loginPage")
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

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        authRequest.setDetails(userDetails);
        authenticationManager.authenticate(authRequest);

        return "redirect:/home";

    }

}
