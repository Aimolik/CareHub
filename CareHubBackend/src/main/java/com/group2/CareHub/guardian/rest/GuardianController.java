package com.group2.CareHub.guardian.rest;

import com.group2.CareHub.guardian.GuardianService;
import com.group2.CareHub.security.jwt.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    private final AuthenticationManager authenticationManager;
    private final GuardianService guardianService;
    private final JwtTokenService jwtTokenService;

    public GuardianController(AuthenticationManager authenticationManager,
                              GuardianService guardianService,
                              JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.guardianService = guardianService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public String createGuardian(@RequestBody GuardianRequestBody guardianRequestBody) {
        return guardianService.createGuardianAccount(guardianRequestBody);
    }
}
