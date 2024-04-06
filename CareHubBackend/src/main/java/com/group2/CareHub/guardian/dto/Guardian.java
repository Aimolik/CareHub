package com.group2.CareHub.guardian.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Guardian {

    private String name;
    private String email;
    private int guardianId;
    private String phone;
    private String address;
}
