package com.usm.UserManagmentService.Service;

import com.usm.UserManagmentService.Entity.Visit;
import com.usm.UserManagmentService.Entity.VisitStatistic;
import com.usm.UserManagmentService.Repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VisitServiceImpl implements VisitService {

    Pattern pattern = Pattern.compile("[/](\\d+)");

    private final CalendarService calendarService;

    private final VisitRepository visitRepository;

    public VisitServiceImpl(CalendarService calendarService, VisitRepository visitRepository) {
        this.calendarService = calendarService;
        this.visitRepository = visitRepository;
    }

    public Visit saveVisitorInfo(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Visit> fetchVisitorList() {
        return (List<Visit>) visitRepository.findAll();
    }

    @Override
    public Page<Visit> findUserActionsWithPagination(int selectedPage, int pageSize) {
        Page<Visit> visitors = visitRepository.findAll((PageRequest.of(selectedPage, pageSize, Sort.by("loggedTime").descending())));

        return visitors;
    }

    @Override
    public List<VisitStatistic> getStatisticsByMethodType(String methodType, Date startDate, Date endDate) throws Exception {

        if (methodType.length() == 0) {
            throw new IllegalArgumentException("Method type cannot be empty");
        }

        List<Visit> visits = visitRepository.findByMethodEndDate(methodType.toUpperCase(), calendarService.dateToLocalDateTime(startDate), calendarService.dateToLocalDateTime(endDate));
        Map<String, VisitStatistic> statistics = new HashMap<>();

        visits.forEach(visit -> {
            String pageName = stripSuffix(visit.getPage());
            if (statistics.containsKey(pageName)) {
                int currentCount = statistics.get(pageName).getCount();
                statistics.get(pageName).setCount(currentCount + 1);
            } else {
                statistics.put(pageName, new VisitStatistic(pageName, visit.getMethod(), 1));
            }

        });

        return new ArrayList<>(statistics.values());
    }

    private String stripSuffix(String page) {

        return pattern.matcher(page).replaceAll("");
    }
}
