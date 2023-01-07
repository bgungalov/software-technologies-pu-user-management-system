package com.usm.UserManagmentService.Service;

import com.usm.UserManagmentService.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    /**
     * @param user new user that will be saved to the database.
     */
    User saveUser(User user);

    /**
     * @param userId the id of the user we want to retrieve.
     */
    User getUserById(int userId);

    /**
     * @param user   new user details to be saved.
     * @param userId find and update user by id.
     */
    User updateUserPartially(User user, int userId);

    /**
     * @param selectedPage select the page of users.
     * @param pageSize     how much users we want to get from the database.
     */
    Page<User> findUsersWithPagination(int selectedPage, int pageSize);

    /**
     * Get all existing users.
     */
    List<User> fetchUserList();

    /**
     * @param user   new user information we want to update.
     * @param userId find the user by ID we want to update.
     */
    User updateUser(User user, int userId);

    /**
     * @param userId this is the id ot the user we want to delete.
     */
    void deleteUserById(int userId);

    /**
     * Get user by email.
     *
     * @param email find user by email.
     */
    User getUserByEmail(String email);
}
