package com.group2.CareHub.child.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChildRequestBody {

    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String medicalInformation;
}
