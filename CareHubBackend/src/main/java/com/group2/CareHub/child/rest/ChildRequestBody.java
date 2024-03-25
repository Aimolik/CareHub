package com.group2.CareHub.child.rest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChildRequestBody {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @Past(message = "Date of birth must be in the past!")
    private LocalDate dateOfBirth;
    @Size(max = 250, message = "Address must be less than 250 characters")
    @NotBlank(message = "Address is required")
    private String address;
    @Size(max = 1000, message = "Medical information must be less than 1000 characters")
    @NotBlank(message = "Medical information is required")
    private String medicalInformation;
}
