package com.usm.UserManagmentService.Service;

import java.util.Optional;

import com.usm.UserManagmentService.Entity.UserCredentials;

public interface UserCredentialsService {

    public UserCredentials findByUsernameAndPassword(String username, String password);

    public UserCredentials loadUserByUsername(String username);
}
