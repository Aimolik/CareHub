package com.group2.CareHub.staff.rest;

import com.group2.CareHub.staff.StaffService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public String registerStaff(@RequestBody StaffRequestBody staffRequestBody) {
        return staffService.registerStaffAccount(staffRequestBody);
    }
}
