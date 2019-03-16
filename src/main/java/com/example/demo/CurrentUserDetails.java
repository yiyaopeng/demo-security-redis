package com.example.demo;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * current user details
 *
 * @author linux_china
 */
public class CurrentUserDetails extends User implements UserDetails {
    private List<GrantedAuthority> grantedAuthorities;

    public CurrentUserDetails(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setEnabled(user.isEnabled());
        setAuthoritiesText(user.getAuthoritiesText());
        this.grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthoritiesText());
    }

    public String getUsername() {
        return String.valueOf(getId());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

}
