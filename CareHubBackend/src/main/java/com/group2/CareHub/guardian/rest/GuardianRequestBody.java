package com.group2.CareHub.guardian.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GuardianRequestBody {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
}
