package com.group2.CareHub.account.rest;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.account.AccountService;
import com.group2.CareHub.common.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/authenticate")
    private ResponseEntity<ResponseBody> authenticate(@RequestBody AccountLoginRequestBody accountLoginRequestBody) {
        ResponseBody responseBody = accountService.authenticateCredentials(accountLoginRequestBody);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/test")
    private String test() {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Test endpoint, you are a " + accountDetails.getRole() + "! Your email is " + accountDetails.getUsername()
                + " with id " + accountDetails.getId() + "!";
    }
}
