package com.usm.UserManagmentService.Controller;

import com.usm.UserManagmentService.Service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class VisitStatisticsController {

    private VisitService visitService;

    public VisitStatisticsController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Object> getStatistics(@RequestParam String methodType,
                                                @RequestParam Date startDate,
                                                @RequestParam Date endDate) {

        try {
            return new ResponseEntity<>(visitService.getStatisticsByMethodType(methodType, startDate, endDate),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
