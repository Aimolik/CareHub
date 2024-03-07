package com.group2.CareHub.child.rest;

import com.group2.CareHub.account.AccountDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/child")
public class ChildController {

    @PostMapping
    public String registerChild(@RequestBody ChildRequestBody childRequestBody) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AccountDetails guardianDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(guardianDetails.getId());
        return "";
    }
}
