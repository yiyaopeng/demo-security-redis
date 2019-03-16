package com.example.demo;

/**
 * domain user
 *
 * @author linux_china
 */
public class User {
    private Long id;
    private String email;
    private String password;
    private String authoritiesText;
    private boolean enabled = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthoritiesText() {
        return authoritiesText;
    }

    public void setAuthoritiesText(String authoritiesText) {
        this.authoritiesText = authoritiesText;
    }
}
