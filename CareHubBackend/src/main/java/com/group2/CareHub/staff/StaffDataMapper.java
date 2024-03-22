package com.group2.CareHub.staff;

import com.group2.CareHub.staff.data.StaffEntity;
import com.group2.CareHub.staff.dto.Staff;
import com.group2.CareHub.staff.rest.StaffRequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StaffDataMapper {

    private final PasswordEncoder passwordEncoder;

    public StaffDataMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public StaffEntity staffRequestBodyToEntity(StaffRequestBody staffRequestBody) {
        String encryptedPassword = passwordEncoder.encode(staffRequestBody.getPassword());
        return StaffEntity.builder()
                .name(staffRequestBody.getName())
                .email(staffRequestBody.getEmail())
                .password(encryptedPassword)
                .position(staffRequestBody.getPosition())
                .contactInfo(staffRequestBody.getContactInfo())
                .build();
    }

    public Staff staffEntityToStaff(StaffEntity staffEntity) {
        return Staff.builder()
                .staffId(staffEntity.getStaffId())
                .name(staffEntity.getName())
                .email(staffEntity.getEmail())
                .position(staffEntity.getPosition())
                .contactInfo(staffEntity.getContactInfo())
                .build();
    }
}
