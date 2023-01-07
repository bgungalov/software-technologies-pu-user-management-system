package com.usm.UserManagmentService.Logger;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usm.UserManagmentService.Entity.Visit;
import com.usm.UserManagmentService.Entity.VisitBuilder;
import com.usm.UserManagmentService.Service.VisitServiceImpl;
import com.usm.UserManagmentService.Utils.HttpRequestResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class VisitorLogger implements HandlerInterceptor {

    @Autowired
    private VisitServiceImpl visitorServiceImpl;

    private VisitBuilder visitBuilder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        final String ip = HttpRequestResponseUtils.getClientIpAddress();
        final String url = HttpRequestResponseUtils.getRequestUrl();
        final String page = HttpRequestResponseUtils.getRequestUri();
        final String refererPage = HttpRequestResponseUtils.getRefererPage();
        final String queryString = HttpRequestResponseUtils.getPageQueryString();
        final String userAgent = HttpRequestResponseUtils.getUserAgent();
        final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
        final LocalDateTime timestamp = LocalDateTime.now();

        Visit visit = new VisitBuilder()
                .setUser(HttpRequestResponseUtils.getAuthenticatedUsername())
                .setIp(ip)
                .setMethod(requestMethod)
                .setUrl(url)
                .setPage(page)
                .setQueryString(queryString)
                .setRefererPage(refererPage)
                .setUserAgent(userAgent)
                .setLoggedTime(timestamp)
                .setUniqueVisit(true).build();

        visitorServiceImpl.saveVisitorInfo(visit);

        return true;
    }

}
