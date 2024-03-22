package com.group2.CareHub.guardian;

import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.exception.exceptions.EntityNotFoundException;
import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.data.GuardianRepository;
import com.group2.CareHub.guardian.dto.Guardian;
import com.group2.CareHub.guardian.rest.GuardianRequestBody;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class GuardianService {

    private final GuardianDataMapper guardianDataMapper;
    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianDataMapper guardianDataMapper,
                           GuardianRepository guardianRepository) {
        this.guardianDataMapper = guardianDataMapper;
        this.guardianRepository = guardianRepository;
    }

    public ResponseBody createGuardianAccount(GuardianRequestBody guardianRequestBody) {
        if(guardianRepository.findByEmail(guardianRequestBody.getEmail()) != null) {
            log.info("Email of {} is already taken!", guardianRequestBody.getEmail());
            return new ResponseBody(400, "Email of " + guardianRequestBody.getEmail() + " for guardian is already taken! Please use another email.");
        }
        log.info("Creating guardian account for email: {}", guardianRequestBody.getEmail());
        GuardianEntity response = guardianRepository.save(guardianDataMapper.guardianRequestBodyToGuardianEntity(guardianRequestBody));
        if(response == null) {
            log.error("Failure in saving guardian account of email: {}", guardianRequestBody.getEmail());
            throw new EntityPersistException("Failure in saving guardian account for email: " + guardianRequestBody.getEmail());
        }
        log.info("Successfully saved guardian account of email: {}", guardianRequestBody.getEmail());
        return new ResponseBody(200, "Successfully saved guardian account of email: " + guardianRequestBody.getEmail());
    }

    public Guardian getGuardianById(int guardianId) {
        Optional<GuardianEntity> response = guardianRepository.findByGuardianId(guardianId);
        if(response.isEmpty()) {
            log.error("No guardian found with id: {}", guardianId);
            throw new EntityNotFoundException("No guardian found with id: " + guardianId);
        }
        return guardianDataMapper.guardianEntityToGuardian(response.get());
    }
}
