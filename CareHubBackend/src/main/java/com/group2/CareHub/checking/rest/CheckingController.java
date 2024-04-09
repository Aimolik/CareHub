package com.group2.CareHub.checking.rest;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.checking.CheckingService;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.CheckingType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checking")
public class CheckingController {

    private final CheckingService checkingService;
    private final ChildService childService;

    public CheckingController(CheckingService checkingService, ChildService childService) {
        this.checkingService = checkingService;
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ResponseBody> check(@RequestBody CheckingRequestBody checkingRequestBody) {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(checkingRequestBody.getCheckingType() == CheckingType.CHECK_IN) {
            return ResponseEntity.ok(checkingService.checkIn(checkingRequestBody));
        }
        return ResponseEntity.ok(checkingService.checkout(checkingRequestBody));
    }
}
