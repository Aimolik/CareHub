package com.group2.CareHub.vehicle;

import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.vehicle.data.VehicleEntity;
import com.group2.CareHub.vehicle.data.VehicleRepository;
import com.group2.CareHub.vehicle.dto.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VehicleService {

    private final VehicleDataMapper vehicleDataMapper;
    private final VehicleRepository vehicleRepository;


    public VehicleService(VehicleDataMapper vehicleDataMapper,
                          VehicleRepository vehicleRepository) {
        this.vehicleDataMapper = vehicleDataMapper;
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseBody registerVehicle(Vehicle vehicle) {
        log.info("Registering vehicle for guardian id: {}", vehicle.getGuardianId());
        VehicleEntity vehicleEntity = vehicleDataMapper.vehicleToEntity(vehicle);
        vehicleRepository.save(vehicleEntity);
        return new ResponseBody(200, "Successfully saved vehicle for guardian id: " + vehicle.getGuardianId());
    }

    public List<Vehicle> getVehiclesByGuardianId(int guardianId) {
        log.info("Fetching vehicles for guardian id: {}", guardianId);
        return vehicleRepository.findVehicleEntitiesByGuardianId(guardianId).stream()
                .map(vehicleDataMapper::vehicleEntityToVehicle).toList();
    }
}
