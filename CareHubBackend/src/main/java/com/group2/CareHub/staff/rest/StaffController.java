package com.group2.CareHub.staff.rest;

import com.group2.CareHub.staff.StaffService;
import com.group2.CareHub.staff.data.StaffEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{staffId}")
    public StaffEntity getStaff(@PathVariable int staffId) {
        return staffService.getStaffById(staffId);
    }
}
