package com.usm.UserManagmentService.Entity;

public class NewRoleRequest {

    private int userId;
    private String roleName;
    private String startDate;
    private String endDate;

    public NewRoleRequest() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "NewRoleRequest{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
