package com.group2.CareHub.child.rest;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.common.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/child")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public String registerChild(@RequestBody ChildRequestBody childRequestBody) {
        AccountDetails guardianDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(guardianDetails.getRole() != Role.GUARDIAN) {
            return "You must be a guardian in order to register a child!";
        }
        return childService.registerChildWithGuardian(childRequestBody, guardianDetails.getId());
    }
}
