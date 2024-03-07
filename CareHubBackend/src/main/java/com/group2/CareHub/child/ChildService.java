package com.group2.CareHub.child;

import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.child.data.ChildRepository;
import com.group2.CareHub.child.rest.ChildRequestBody;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public String registerChildWithGuardian(ChildRequestBody childRequestBody, int guardianId) {
        log.info("Creating child for guardian id {}", guardianId);
        ChildEntity response = childRepository.save(childRequestBodyToEntity(childRequestBody, guardianId));
        if(response == null) {
            log.error("Failure in saving child entity for guardian id: {}", guardianId);
            throw new EntityPersistException("Failure in saving child entity for guardian id: " + guardianId);
        }
        log.info("Successfully saved child for guardian id: {}", guardianId);
        return "Successfully saved child for guardian id: " + guardianId;
    }

    private ChildEntity childRequestBodyToEntity(ChildRequestBody childRequestBody, int guardianId) {
        return ChildEntity.builder()
                .guardian_id(guardianId)
                .name(childRequestBody.getName())
                .dateOfBirth(childRequestBody.getDateOfBirth())
                .address(childRequestBody.getAddress())
                .medicalInformation(childRequestBody.getMedicalInformation())
                .build();
    }
}
