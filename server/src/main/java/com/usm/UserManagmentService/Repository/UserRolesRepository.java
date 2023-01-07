package com.usm.UserManagmentService.Repository;

import com.usm.UserManagmentService.Entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    Optional<List<UserRoles>> findRolesByUserId(int userId);
}
