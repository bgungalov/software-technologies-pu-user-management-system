package com.usm.UserManagmentService.Controller;

import com.usm.UserManagmentService.Entity.Visit;
import com.usm.UserManagmentService.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserActionsController {

    private final VisitService visitService;

    @Autowired
    public UserActionsController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/useractions")
    public ResponseEntity<List<Visit>> fetchVisitorList() {
        return new ResponseEntity<>(visitService.fetchVisitorList(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "pagination/useractions/{selectedPage}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<Page<Visit>> findUserActionsWithPagination(@PathVariable("selectedPage") int selectedPage, @PathVariable("pageSize") int pageSize) {
        Page<Visit> currentUserPage = visitService.findUserActionsWithPagination(selectedPage, pageSize);

        return new ResponseEntity<>(currentUserPage,
                HttpStatus.OK);
    }
}
