package com.usm.UserManagmentService.Service;

import java.util.ArrayList;
import java.util.List;

import com.usm.UserManagmentService.Entity.UserCredentials;
import com.usm.UserManagmentService.Entity.UserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JWTUserDetailsService implements UserDetailsService {

    private final UserRolesService userRolesService;
    private final UserCredentialsService userCredentialsService;
    private final UserService userService;

    @Autowired
    public JWTUserDetailsService(UserRolesService userRolesService, UserCredentialsService userCredentialsService, UserService userService) {
        this.userRolesService = userRolesService;
        this.userCredentialsService = userCredentialsService;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.usm.UserManagmentService.Entity.User user = userService.getUserByEmail(username);

        UserCredentials userCredentials = this.userCredentialsService.loadUserByUsername(username);

        if (username.equals(userCredentials.getUsername())) {
            return new User(userCredentials.getUsername(), userCredentials.getPassword(), getGrantedAuthority(userRolesService.extractActiveRolesFromUser(user.getRoles())));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private List<GrantedAuthority> getGrantedAuthority(List<UserRoles> userRoles) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        userRoles.forEach(userRole -> grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRoleName())));

        return grantedAuthorities;
    }
}