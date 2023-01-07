package com.usm.UserManagmentService.Repository;

import com.usm.UserManagmentService.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is Extension of CrudRepository and
 * is responsible to provide additional methods to retrieve
 * entities using the pagination and sorting abstraction.
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    public User getUserByEmail(String email);
}
