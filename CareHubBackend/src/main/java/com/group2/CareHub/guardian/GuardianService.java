package com.group2.CareHub.guardian;

import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.data.GuardianRepository;
import com.group2.CareHub.guardian.rest.GuardianRequestBody;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final PasswordEncoder passwordEncoder;

    public GuardianService(GuardianRepository guardianRepository,
                           PasswordEncoder passwordEncoder) {
        this.guardianRepository = guardianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createGuardianAccount(GuardianRequestBody guardianRequestBody) {
        if(guardianRepository.findByEmail(guardianRequestBody.getEmail()) != null) {
            log.info("Email of {} is already taken!", guardianRequestBody.getEmail());
            return "Email of " + guardianRequestBody.getEmail() + " for guardian is already taken! Please use another email.";
        }
        log.info("Creating guardian account for email: {}", guardianRequestBody.getEmail());
        GuardianEntity response = guardianRepository.save(guardianRequestBodyToEntity(guardianRequestBody));
        if(response == null) {
            log.error("Failure in saving guardian account of email: {}", guardianRequestBody.getEmail());
            throw new EntityPersistException("Failure in saving guardian account for email: " + guardianRequestBody.getEmail());
        }
        log.info("Successfully saved guardian account of email: {}", guardianRequestBody.getEmail());
        return "Successfully saved guardian account of email: " + guardianRequestBody.getEmail();
    }

    private GuardianEntity guardianRequestBodyToEntity(GuardianRequestBody guardianRequestBody) {
        String encryptedPassword = passwordEncoder.encode(guardianRequestBody.getPassword());
        return GuardianEntity.builder()
                .name(guardianRequestBody.getName())
                .email(guardianRequestBody.getEmail())
                .password(encryptedPassword)
                .address(guardianRequestBody.getAddress())
                .phone(guardianRequestBody.getPhone())
                .build();
    }
}
