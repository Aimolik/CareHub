package com.group2.CareHub.guardian.rest;

import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.guardian.GuardianService;
import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.dto.Guardian;
import com.group2.CareHub.security.jwt.JwtTokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    private final GuardianService guardianService;
    private final ChildService childService;

    public GuardianController(GuardianService guardianService, ChildService childService) {
        this.guardianService = guardianService;
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> createGuardian(@RequestBody @Valid GuardianRequestBody guardianRequestBody) {
        ResponseBody responseBody = guardianService.createGuardianAccount(guardianRequestBody);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{guardianId}/children")
    public List<ChildEntity> getChildrenByGuardianId(@PathVariable int guardianId) {
        return childService.getChildrenByGuardianId(guardianId);
    }

    @GetMapping("/{guardianId}")
    public Guardian getGuardian(@PathVariable int guardianId) {
        return guardianService.getGuardianById(guardianId);
    }
}
