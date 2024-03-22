package com.group2.CareHub.staff.rest;

import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.staff.StaffService;
import com.group2.CareHub.staff.data.StaffEntity;
import com.group2.CareHub.staff.dto.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> registerStaff(@RequestBody StaffRequestBody staffRequestBody) {
        ResponseBody responseBody = staffService.registerStaffAccount(staffRequestBody);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable int staffId) {
        return staffService.getStaffById(staffId);
    }
}
