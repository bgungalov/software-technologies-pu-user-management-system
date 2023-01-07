package com.usm.UserManagmentService.Service;

import com.usm.UserManagmentService.Entity.NewRoleRequest;
import com.usm.UserManagmentService.Entity.User;
import com.usm.UserManagmentService.Entity.UserRoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRolesService {

    List<UserRoles> findAllRoles();

    UserRoles saveRole(UserRoles role);

    Optional<UserRoles> findRolesByUserId(int id);

    void deleteRoleById(int id);

    void assignUserRole(int userId, int roleId);

    void assignNewRole(NewRoleRequest newRoleRequest) throws Exception;

    void unassignUserRole(int userId, int roleId);

    List<UserRoles> getUserRoles(User user);

    UserRoles getActiveCurrentUserRole(int userId);

    List<UserRoles> extractActiveRolesFromUser(List<UserRoles> roles);

    List<UserRoles> getAllUserRoles(int userId);
}
