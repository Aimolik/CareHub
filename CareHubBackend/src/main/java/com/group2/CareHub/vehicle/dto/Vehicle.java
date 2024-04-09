package com.group2.CareHub.vehicle.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Vehicle {

    private int vehicleId;
    private int guardianId;
    private String vehicleType;
    private String licensePlate;
    private String color;
}
