package com.usm.UserManagmentService.Repository;

import com.usm.UserManagmentService.Entity.UserCredentials;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Integer> {
    public UserCredentials findByUsernameAndPassword(String username, String password);

    public UserCredentials findByUsername(String username);
}
