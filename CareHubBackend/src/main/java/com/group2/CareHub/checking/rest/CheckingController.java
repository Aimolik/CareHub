package com.group2.CareHub.checking.rest;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.checking.CheckingService;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.CheckingType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checking")
public class CheckingController {

    private final CheckingService checkingService;

    public CheckingController(CheckingService checkingService) {
        this.checkingService = checkingService;
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
