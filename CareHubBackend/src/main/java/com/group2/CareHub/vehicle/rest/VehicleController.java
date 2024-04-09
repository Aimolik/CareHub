package com.group2.CareHub.vehicle.rest;


import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.Role;
import com.group2.CareHub.vehicle.VehicleDataMapper;
import com.group2.CareHub.vehicle.VehicleService;
import com.group2.CareHub.vehicle.dto.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleDataMapper vehicleDataMapper;
    private final VehicleService vehicleService;

    public VehicleController(VehicleDataMapper vehicleDataMapper, VehicleService vehicleService) {
        this.vehicleDataMapper = vehicleDataMapper;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> registerVehicle(@RequestBody VehicleRequestBody vehicleRequestBody) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(accountDetails.getRole() != Role.GUARDIAN) {
            return ResponseEntity.ok(new ResponseBody(403, "Only guardians can register vehicles"));
        }
        ResponseBody responseBody = vehicleService.registerVehicle(vehicleDataMapper.requestBodyToVehicle(vehicleRequestBody, accountDetails.getId()));
        return ResponseEntity.ok(responseBody);
    }
}
