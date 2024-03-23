package com.group2.CareHub.account.rest;

import com.group2.CareHub.common.enumeration.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountLoginRequestBody {

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
