package com.usm.UserManagmentService.Service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public interface CalendarService {

    Date getCurrentDate();

    Date setEndDate(int year);

    Date generateDateFromDDMMYYYY(String dateExpression) throws ParseException;

    LocalDateTime dateToLocalDateTime(Date date);
}
