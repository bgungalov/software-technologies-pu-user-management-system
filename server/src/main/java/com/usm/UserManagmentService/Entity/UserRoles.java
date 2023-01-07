package com.usm.UserManagmentService.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_roles_details")
public class UserRoles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String roleName;

    Date startDate;

    Date endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "id")
    User user;

    public UserRoles() {
    }

    public UserRoles(String roleName) {
        this.roleName = roleName;
    }

    public UserRoles(String roleName, Date startDate, Date endDate) {
        this.roleName = roleName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserRoles(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
