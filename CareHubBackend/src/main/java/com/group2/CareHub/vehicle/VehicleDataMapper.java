package com.group2.CareHub.vehicle;

import com.group2.CareHub.vehicle.data.VehicleEntity;
import com.group2.CareHub.vehicle.dto.Vehicle;
import com.group2.CareHub.vehicle.rest.VehicleRequestBody;
import org.springframework.stereotype.Component;

@Component
public class VehicleDataMapper {

    public Vehicle requestBodyToVehicle(VehicleRequestBody vehicleRequestBody, int guardianId) {
        return Vehicle.builder()
                .licensePlate(vehicleRequestBody.getLicensePlate())
                .vehicleType(vehicleRequestBody.getVehicleType())
                .color(vehicleRequestBody.getColor())
                .guardianId(guardianId)
                .build();
    }

    public VehicleEntity vehicleToEntity(Vehicle vehicle) {
        return VehicleEntity.builder()
                .vehicleId(vehicle.getVehicleId())
                .licensePlate(vehicle.getLicensePlate())
                .vehicleType(vehicle.getVehicleType())
                .color(vehicle.getColor())
                .guardianId(vehicle.getGuardianId())
                .build();
    }

    public Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity) {
        return Vehicle.builder()
                .vehicleId(vehicleEntity.getVehicleId())
                .licensePlate(vehicleEntity.getLicensePlate())
                .vehicleType(vehicleEntity.getVehicleType())
                .color(vehicleEntity.getColor())
                .guardianId(vehicleEntity.getGuardianId())
                .build();
    }
}
