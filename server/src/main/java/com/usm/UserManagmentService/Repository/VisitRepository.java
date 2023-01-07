package com.usm.UserManagmentService.Repository;

import com.usm.UserManagmentService.Entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

    Page<Visit> findByLoggedTime(LocalDateTime loggedTime, Pageable pageable);

    @Query(value = "SELECT * FROM users.user_action where method = ?1 and (logged_time between ?2 and ?3)", nativeQuery = true)
    List<Visit> findByMethodEndDate(String method, LocalDateTime startDate, LocalDateTime endDate);
}
