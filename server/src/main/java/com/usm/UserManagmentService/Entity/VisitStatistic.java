package com.usm.UserManagmentService.Entity;

public class VisitStatistic {

    private String queryName;
    private String queryMethod;
    private int count;

    public VisitStatistic() {
    }

    public VisitStatistic(String queryName, String queryMethod, int count) {
        this.queryName = queryName;
        this.queryMethod = queryMethod;
        this.count = count;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryMethod() {
        return queryMethod;
    }

    public void setQueryMethod(String queryMethod) {
        this.queryMethod = queryMethod;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
