package com.group2.CareHub.staff.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StaffRequestBody {

    private String name;
    private String email;
    private String password;
    private String position;
    private String contactInfo;
}
