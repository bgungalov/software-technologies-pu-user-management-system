package com.usm.UserManagmentService.Controller;

import com.usm.UserManagmentService.Entity.NewRoleRequest;
import com.usm.UserManagmentService.Entity.UserRoles;
import com.usm.UserManagmentService.Service.UserRolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RolesController {

    private final UserRolesService userRolesService;

    public RolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    /***
     * POST Method for creating new role.
     *
     * @param userRole role's name
     * @return newly created role.
     */
    @PostMapping("/roles")
    public ResponseEntity<UserRoles> addNewRole(UserRoles userRole) {
        return new ResponseEntity<>(userRolesService.saveRole(userRole),
                HttpStatus.OK);
    }

    /**
     * GET Method for finding role by ID.
     *
     * @param id role's id to search for.
     * @return found role.
     */
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<UserRoles>> findRoleById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userRolesService.findRolesByUserId(id),
                HttpStatus.OK);
    }

    /**
     * GET Method for getting all roles in the database.
     *
     * @return all roles.
     */
    @GetMapping("/roles")
    public ResponseEntity<List<UserRoles>> fetchUserRoles() {
        return new ResponseEntity<>(userRolesService.findAllRoles(),
                HttpStatus.OK);
    }

    /**
     * PUT Method for updating role by ID.
     *
     * @param userRole new role information.
     * @return newly updated role.
     */
    @PutMapping("/roles/{id}")
    public ResponseEntity<UserRoles> updateRole(@RequestBody UserRoles userRole) {
        return new ResponseEntity<>(userRolesService.saveRole(userRole),
                HttpStatus.OK);
    }

    /**
     * DELETE Method for deleting role by ID.
     *
     * @param roleId find role by ID and delete it.
     * @return confirmation message.
     */
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteUserRoleById(@PathVariable("id") int roleId) {
        userRolesService.deleteRoleById(roleId);

        return new ResponseEntity<>("Role deleted Successfully!",
                HttpStatus.OK);
    }

    /**
     * POST Method for assigning role to user
     *
     * @param userId find the user we want to assign role to
     * @param roleId find the role id we want to assign
     * @return HttpStatus.OK 200
     */
    @RequestMapping("/role/assign/{userId}/{roleId}")
    public ResponseEntity<Object> assignUserRole(@PathVariable int userId, @PathVariable int roleId) {
        userRolesService.assignUserRole(userId, roleId);

        return new ResponseEntity<>("result successful result",
                HttpStatus.OK);
    }

    /**
     * POST Method for assigning user role by role name
     *
     * @param newRoleRequest the new role that will be
     *                       created and assigned to the user
     * @return confirmation string
     */
    @PostMapping("/assign/role/")
    public ResponseEntity<Object> assignUserRoleByRoleName(@RequestBody NewRoleRequest newRoleRequest) {
        try {
            userRolesService.assignNewRole(newRoleRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Assigned successfully",
                HttpStatus.OK);
    }

    /**
     * POST Method for unassigning role from user
     *
     * @param userId find the user we want to unassign role
     * @param roleId find the role id we want to unassign
     * @return confirmation string
     */
    @RequestMapping("/role/unassign/{userId}/{roleId}")
    public ResponseEntity<String> unassignUserRole(@PathVariable int userId, @PathVariable int roleId) {
        userRolesService.unassignUserRole(userId, roleId);

        return new ResponseEntity<>("User ID: " + userId + " has been unassigned from role with ID: " + roleId,
                HttpStatus.OK);
    }

    @GetMapping("/roles/user")
    public ResponseEntity<List<UserRoles>> getUserRoles(@RequestParam int userId) {
        List<UserRoles> roles = userRolesService.getAllUserRoles(userId);

        return new ResponseEntity<>(roles,
                HttpStatus.OK);
    }
}
