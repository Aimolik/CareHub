package com.group2.CareHub.guardian.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuardianAuthenticateRequestBody {
    private String email;
    private String password;
}
