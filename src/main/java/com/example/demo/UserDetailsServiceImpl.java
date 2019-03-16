//package com.example.demo;
//
//
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * user details service implementation
// *
// * @author linux_china
// */
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("admin@demo.com");
//        user.setPassword("$2a$10$18yNOWoB1r0zXJhccDVsuOhHudZzhc9VcPHXO6mC5xdFsCIxaa6ty");
//        user.setAuthoritiesText("ROLE_ADMIN");
//        user.setEnabled(true);
//        return new CurrentUserDetails(user);
//    }
//}
