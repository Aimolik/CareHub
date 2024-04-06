package com.group2.CareHub.guardian;

import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.dto.Guardian;
import com.group2.CareHub.guardian.rest.GuardianRequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GuardianDataMapper {

    private final PasswordEncoder passwordEncoder;

    public GuardianDataMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public GuardianEntity guardianRequestBodyToGuardianEntity(GuardianRequestBody guardianRequestBody) {
        String encryptedPassword = passwordEncoder.encode(guardianRequestBody.getPassword());
        return GuardianEntity.builder()
                .name(guardianRequestBody.getName())
                .email(guardianRequestBody.getEmail())
                .password(encryptedPassword)
                .address(guardianRequestBody.getAddress())
                .phone(guardianRequestBody.getPhone())
                .build();
    }

    public Guardian guardianEntityToGuardian(GuardianEntity guardianEntity) {
        return Guardian.builder()
                .name(guardianEntity.getName())
                .email(guardianEntity.getEmail())
                .guardianId(guardianEntity.getGuardianId())
                .phone(guardianEntity.getPhone())
                .address(guardianEntity.getAddress())
                .build();
    }
}
