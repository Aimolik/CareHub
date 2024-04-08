package com.group2.CareHub.guardian.rest;

import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.guardian.GuardianService;
import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.dto.Guardian;
import com.group2.CareHub.security.jwt.JwtTokenService;
import com.group2.CareHub.vehicle.VehicleService;
import com.group2.CareHub.vehicle.dto.Vehicle;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    private final GuardianService guardianService;
    private final VehicleService vehicleService;
    private final ChildService childService;

    public GuardianController(GuardianService guardianService, VehicleService vehicleService, ChildService childService) {
        this.guardianService = guardianService;
        this.vehicleService = vehicleService;
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> createGuardian(@RequestBody @Valid GuardianRequestBody guardianRequestBody) {
        ResponseBody responseBody = guardianService.createGuardianAccount(guardianRequestBody);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{guardianId}/children")
    public List<ChildEntity> getChildrenByGuardianId(@PathVariable int guardianId) {
        return childService.getChildrenByGuardianId(guardianId);
    }

    @GetMapping("/{guardianId}")
    public Guardian getGuardian(@PathVariable int guardianId) {
        return guardianService.getGuardianById(guardianId);
    }

    @GetMapping("/{guardianId}/vehicles")
    public List<Vehicle> getVehiclesByGuardianId(@PathVariable int guardianId) {
        return vehicleService.getVehiclesByGuardianId(guardianId);
    }

    @GetMapping("/{guardianId}/checkin")
    public List<ChildEntity> getChildrenWithCheckinStatus(@PathVariable int guardianId) {
        return childService.getChildrenByGuardianIdAndAttendanceStatus(guardianId, AttendanceStatus.CHECKED_IN);
    }

    @GetMapping("/{guardianId}/checkout")
    public List<ChildEntity> getChildrenWithCheckoutStatus(@PathVariable int guardianId) {
        return childService.getChildrenByGuardianIdAndAttendanceStatus(guardianId, AttendanceStatus.CHECKED_OUT);
    }
}
