package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRedisHttpSession
@RestController
public class DemoSecurityRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityRedisApplication.class, args);
	}
	

	
	@GetMapping("/home")
	public String getUser(@AuthenticationPrincipal UserDetails user) {
		return new StringBuilder("Hello, ").append(user.getUsername()).append("!").toString();
	}


	
}
