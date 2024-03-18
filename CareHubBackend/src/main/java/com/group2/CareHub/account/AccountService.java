package com.group2.CareHub.account;

import com.group2.CareHub.account.rest.AccountLoginRequestBody;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.security.jwt.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class AccountService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AccountService(AuthenticationManager authenticateManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticateManager;
        this.jwtTokenService = jwtTokenService;
    }

    public ResponseBody authenticateCredentials(@RequestBody AccountLoginRequestBody accountLoginRequestBody) {
        String email = accountLoginRequestBody.getEmail() + "#" + accountLoginRequestBody.getRole().name();
        String password = accountLoginRequestBody.getPassword();
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(credentials);
        } catch (AuthenticationException authenticationException) {
            log.info(authenticationException.getMessage());
            return new ResponseBody(400, "Invalid credentials! More info: " + authenticationException.getMessage());
        }
        String jwt = jwtTokenService.generateToken(email);
        return new ResponseBody(200, jwt);
    }
}
