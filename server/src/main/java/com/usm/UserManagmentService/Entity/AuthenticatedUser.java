package com.usm.UserManagmentService.Entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AuthenticatedUser {
    String username;
    List<GrantedAuthority> userRoles = new ArrayList<>();
    String name;

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(String username, List<GrantedAuthority> userRoles, String name) {
        this.username = username;
        this.userRoles = userRoles;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<GrantedAuthority> userRoles) {
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
