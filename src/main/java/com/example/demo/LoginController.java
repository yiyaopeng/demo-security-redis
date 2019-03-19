package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(description = "账户管理")
public class LoginController {





    @GetMapping("/loginPage")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    @ResponseBody
    public String user(@AuthenticationPrincipal UserDetails user) {
        return new StringBuilder("Hello, ").append(user.getUsername()).append("!").toString();
    }
    @PostMapping("/home")
    @ResponseBody
    public String getUser(@AuthenticationPrincipal UserDetails user) {
        return new StringBuilder("Hello, ").append(user.getUsername()).append("!").toString();
    }

    @RequestMapping(path = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@ApiParam(required = true, name = "username", value = "手机号或邮箱",defaultValue = "user") @RequestParam String username ,
                          @ApiParam(required = true, name = "password", value = "密码",defaultValue = "password") @RequestParam String password ,
                          HttpServletRequest request, HttpServletResponse response) {
        UserDetailsService userDetailsService=new UserDetailsServiceImpl();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        authRequest.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authRequest);

        return "redirect:/home";

    }

}
