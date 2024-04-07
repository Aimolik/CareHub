package com.group2.CareHub.vehicle.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VehicleRequestBody {

    private String vehicleType;
    private String licensePlate;
    private String color;
}
