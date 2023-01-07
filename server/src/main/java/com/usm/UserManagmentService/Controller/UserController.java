package com.usm.UserManagmentService.Controller;

import com.usm.UserManagmentService.Entity.User;
import com.usm.UserManagmentService.Entity.UserRoles;
import com.usm.UserManagmentService.Service.CalendarService;
import com.usm.UserManagmentService.Service.UserRolesService;
import com.usm.UserManagmentService.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.usm.UserManagmentService.Constants.Constants.ROLE_BASIC_USER;

/**
 * Controller class for processing requests.
 */
@RestController
public class UserController {

    private final UserService userService;
    private final UserRolesService userRolesService;
    private final CalendarService calendarService;

    public UserController(UserService userService, UserRolesService userRolesService, CalendarService calendarService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
        this.calendarService = calendarService;
    }

    /**
     * POST Method for saving new user.
     *
     * @param user new user that will be saved to the database.
     * @return newly saved user.
     */

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(
            @Validated @RequestBody User user) {
        User userDB = userService.saveUser(user);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Date today = calendarService.getCurrentDate();

        UserRoles userRolesDB = userRolesService.saveRole(new UserRoles(ROLE_BASIC_USER, today,
                calendarService.setEndDate(year)));
        userRolesService.assignUserRole(user.getId(), userRolesDB.getId());

        return new ResponseEntity<>(userDB,
                HttpStatus.OK);
    }

    /**
     * GET Method for finding user by ID.
     *
     * @param id user's id to search for.
     * @return found user.
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id),
                HttpStatus.OK);
    }

    /**
     * PATCH Method for updating user information partially.
     *
     * @param userId      find user to update by ID.
     * @param userDetails new user details to be saved.
     * @return updated user.
     */
    @PatchMapping(value = "users/{id}", consumes = "application/json")
    public ResponseEntity<User> updateUserPartially(@PathVariable("id") int userId, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUserPartially(userDetails, userId));
    }

    /**
     * GET Method for showing users in pages and page size.
     *
     * @param selectedPage page to show.
     * @param pageSize     size of page to show.
     * @return selected page with selected page size.
     */
    @RequestMapping(value = "pagination/users/{selectedPage}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> findUsersWithPagination(@PathVariable("selectedPage") int selectedPage,
                                                              @PathVariable("pageSize") int pageSize) {
        Page<User> currentUserPage = userService.findUsersWithPagination(selectedPage, pageSize);

        return new ResponseEntity<>(currentUserPage,
                HttpStatus.OK);
    }

    /**
     * GET Method for getting all users in the database.
     *
     * @return all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchUserList(),
                HttpStatus.OK);
    }

    /**
     * PUT Method for updating user by ID.
     *
     * @param user   new user information.
     * @param userId find user by ID and update the information.
     * @return newly updated user.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int userId) {
        return new ResponseEntity<>(userService.updateUser(user, userId),
                HttpStatus.OK);
    }
    /**
     * DELETE Method for deleting user by ID.
     *
     * @param userId find user by ID and delete it.
     * @return confirmation message.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int userId) {
        userService.deleteUserById(userId);

        return new ResponseEntity<>("Deleted Successfully!",
                HttpStatus.OK);
    }
}
