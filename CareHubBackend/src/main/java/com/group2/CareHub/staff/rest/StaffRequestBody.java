package com.group2.CareHub.staff.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StaffRequestBody {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @Email
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(max = 100, message = "Password must be less than 100 characters")
    private String password;
    @NotBlank(message = "Position is required")
    @Size(max = 50, message = "Position must be less than 50 characters")
    private String position;
    @NotBlank(message = "Contact info is required")
    @Size(max = 300, message = "Contact info must be less than 300 characters")
    private String contactInfo;
}
