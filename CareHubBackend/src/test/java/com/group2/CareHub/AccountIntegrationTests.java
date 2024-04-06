package com.group2.CareHub;

import com.group2.CareHub.account.AccountService;
import com.group2.CareHub.account.rest.AccountLoginRequestBody;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.Role;
import com.group2.CareHub.guardian.GuardianService;
import com.group2.CareHub.guardian.rest.GuardianRequestBody;
import com.group2.CareHub.staff.StaffService;
import com.group2.CareHub.staff.rest.StaffRequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class AccountIntegrationTests {

    @Autowired
    private GuardianService guardianService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private AccountService accountService;

    @Test
    void testRegistrationAndLogin() {
        GuardianRequestBody guardianRequestBody = GuardianRequestBody.builder()
                .email("Guardian@gmail.com")
                .password("Password")
                .address("Address")
                .phone("777-777-777")
                .name("Garfield")
                .build();

        ResponseBody response = guardianService.createGuardianAccount(guardianRequestBody);
        assert(response.statusCode() == 200);

        StaffRequestBody staffRequestBody = StaffRequestBody.builder()
                .email("Staff@gmail.com")
                .password("Password")
                .contactInfo("Contact Info")
                .position("Caretaker")
                .name("Jon")
                .build();

        response = staffService.registerStaffAccount(staffRequestBody);
        assert(response.statusCode() == 200);

        AccountLoginRequestBody guardianLoginRequestBody = new AccountLoginRequestBody("Guardian@gmail.com", "Password", Role.GUARDIAN);
        response = accountService.authenticateCredentials(guardianLoginRequestBody);
        assert(response.statusCode() == 200);

        AccountLoginRequestBody staffLoginRequestBody = new AccountLoginRequestBody("Staff@gmail.com", "Password", Role.STAFF);
        response = accountService.authenticateCredentials(staffLoginRequestBody);
        assert(response.statusCode() == 200);

        AccountLoginRequestBody invalidLoginRequestBody = new AccountLoginRequestBody("dfdsfasd", "adsfadsf", Role.STAFF);
        response = accountService.authenticateCredentials(invalidLoginRequestBody);
        assert(response.statusCode() == 400);
    }
}
