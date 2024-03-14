package com.group2.CareHub.staff;

import com.group2.CareHub.exception.exceptions.EntityNotFoundException;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import com.group2.CareHub.staff.data.StaffEntity;
import com.group2.CareHub.staff.data.StaffRepository;
import com.group2.CareHub.staff.rest.StaffRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    public StaffService(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String registerStaffAccount(StaffRequestBody staffRequestBody) {
        if(staffRepository.findByEmail(staffRequestBody.getEmail()) != null) {
            return "Email of " + staffRequestBody.getEmail() + " for staff is already taken! Please use another email.";
        }
        log.info("Creating staff account for email: {}", staffRequestBody.getEmail());
        StaffEntity staffEntity = staffRepository.save(staffRequestBodyToEntity(staffRequestBody));
        if(staffEntity == null) {
            log.error("Failure in saving staff account of email: {}", staffEntity.getEmail());
            throw new EntityPersistException("Failure in saving staff account for email: " + staffEntity.getEmail());
        }
        log.info("Successfully saved staff account of email: {}", staffRequestBody.getEmail());
        return "Successfully saved staff account of email: " + staffEntity.getEmail();
    }

    public StaffEntity getStaffById(int staffId) {
        // TODO Map to another object so password isn't included
        Optional<StaffEntity> response = staffRepository.findByStaffId(staffId);
        if(response.isEmpty()) {
            log.error("Staff with id: {} not found!", staffId);
            throw new EntityNotFoundException("Staff with id: " + staffId + " not found!");
        }
        return response.get();
    }

    private StaffEntity staffRequestBodyToEntity(StaffRequestBody staffRequestBody) {
        String encryptedPassword = passwordEncoder.encode(staffRequestBody.getPassword());
        return StaffEntity.builder()
                .name(staffRequestBody.getName())
                .email(staffRequestBody.getEmail())
                .password(encryptedPassword)
                .position(staffRequestBody.getPosition())
                .contactInfo(staffRequestBody.getContactInfo())
                .build();
    }
}
