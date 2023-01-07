package com.usm.UserManagmentService.Service;

import java.util.Optional;

import com.usm.UserManagmentService.Entity.UserCredentials;
import com.usm.UserManagmentService.Repository.UserCredentialsRepository;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private final UserCredentialsRepository userCredentialsRepository;

    @Autowired
    public UserCredentialsServiceImpl(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public UserCredentials findByUsernameAndPassword(String username, String password) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsernameAndPassword(username, password);

        return userCredentials;
    }

    @Override
    public UserCredentials loadUserByUsername(String username) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);


        return userCredentials;
    }
}
