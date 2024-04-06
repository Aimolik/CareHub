package com.group2.CareHub.staff.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Staff {

    private String name;
    private String email;
    private int staffId;
    private String position;
    private String contactInfo;
}
