package com.usm.UserManagmentService.Service;

import com.usm.UserManagmentService.Entity.User;
import com.usm.UserManagmentService.Entity.UserDetails;
import com.usm.UserManagmentService.Entity.UserDetailsBuilder;
import com.usm.UserManagmentService.Repository.UserRepository;

import com.usm.UserManagmentService.Repository.UserRolesRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserRolesRepository userRolesRepository;

    private final UserDetailsBuilder userDetailsBuilder = new UserDetailsBuilder();


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    /**
     * Calls user repository to save new user.
     *
     * @param user new user that will be saved to the database.
     * @return newly saved user instance with given id.
     */
    @Override
    public User saveUser(User user) {
        if (userRepository.getUserByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        System.out.println("User exists");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email exists");
    }

    /**
     * Calls user repository to get all users.
     *
     * @return all users.
     */
    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Calls user repository to get specific user.
     *
     * @param userId the id of the user we want to retrieve.
     * @return the specific user.
     */
    @Override
    public User getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? user.get() : null;
    }

    /**
     * Call user repository to partially update specific user.
     *
     * @param userDetails new user details to be saved.
     * @param userId      find and update user by id.
     * @return newly saved user with updated details.
     */
    @Override
    public User updateUserPartially(User userDetails, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        final User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    /**
     * Pagination request
     * Call user repository to measure the size of the list of searched users.
     *
     * @param selectedPage select the page of users.
     * @param pageSize     how much users we want to get from the database.
     * @return the required number of page and the how many users per page.
     */
    @Override
    public Page<User> findUsersWithPagination(int selectedPage, int pageSize) {
        Page<User> users = userRepository.findAll((PageRequest.of(selectedPage, pageSize)));

        return users;
    }

    /**
     * Call user repository to update existing user.
     *
     * @param user   нew user information we want to update.
     * @param userId find the user by ID we want to update.
     * @return the user with the updated information.
     */
    @Override
    public User updateUser(User user, int userId) {
        User userDB = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            userDB.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getMiddleName()) && !"".equalsIgnoreCase(user.getMiddleName())) {
            userDB.setMiddleName(user.getMiddleName());
        }

        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            userDB.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userDB.setEmail(user.getEmail());
        }

        if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
            userDB.setPhoneNumber(user.getPhoneNumber());
        }

        if (Objects.nonNull(user.getUserDetails())) {
            UserDetails userDetails = user.getUserDetails();

            userDB.setUserDetails(
                    this.userDetailsBuilder
                            .setId(userDB.getUserDetails().getId())
                            .setUcn(userDetails.getUcn())
                            .setAddress(userDetails.getAddress())
                            .setCountry(userDetails.getCountry())
                            .setCity(userDetails.getCity())
                            .setCityPostCode(userDetails.getCityPostCode())
                            .setStreet(userDetails.getStreet())
                            .build());
        }

        return userRepository.save(userDB);
    }

    /**
     * Call user repository to delete existing user.
     *
     * @param userId this is the id ot the user we want to delete.
     */
    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}