package com.group2.CareHub.child.rest;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/child")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> registerChild(@RequestBody @Valid ChildRequestBody childRequestBody) {
        AccountDetails guardianDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(guardianDetails.getRole() != Role.GUARDIAN) {
            return ResponseEntity.ok(new ResponseBody(403, "You must be a guardian in order to register a child!"));
        }
        ResponseBody responseBody = childService.registerChildWithGuardian(childRequestBody, guardianDetails.getId());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{childId}")
    public ChildEntity getChild(@PathVariable int childId) {
        return childService.getChildByChildId(childId);
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<ResponseBody> deleteChild(@PathVariable int childId) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseBody responseBody = childService.deleteChild(childId, accountDetails);
        return ResponseEntity.ok(responseBody);
    }
}
