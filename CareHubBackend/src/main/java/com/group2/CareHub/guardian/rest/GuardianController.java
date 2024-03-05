package com.group2.CareHub.guardian.rest;

import com.group2.CareHub.guardian.GuardianService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @PostMapping
    public String createGuardian(@RequestBody GuardianRequestBody guardianRequestBody) {
        return guardianService.createGuardianAccount(guardianRequestBody);
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint";
    }
}
