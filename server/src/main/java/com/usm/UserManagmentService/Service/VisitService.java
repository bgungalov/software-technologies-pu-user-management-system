package com.usm.UserManagmentService.Service;

import com.usm.UserManagmentService.Entity.Visit;
import com.usm.UserManagmentService.Entity.VisitStatistic;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface VisitService {
    public Visit saveVisitorInfo(Visit visit);

    public List<Visit> fetchVisitorList();

    Page<Visit> findUserActionsWithPagination(int selectedPage, int pageSize);

    List<VisitStatistic> getStatisticsByMethodType(String methodType, Date startDate, Date endDate) throws Exception;
}
