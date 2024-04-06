package com.group2.CareHub.guardian.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuardianRequestBody {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @Email
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;
    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone must be less than 20 characters")
    private String phone;
    @NotBlank(message = "Address is required")
    @Size(max = 150, message = "Address must be less than 150 characters")
    private String address;
    @NotBlank(message = "Password is required")
    @Size(max = 100, message = "Password must be less than 100 characters")
    private String password;

}
