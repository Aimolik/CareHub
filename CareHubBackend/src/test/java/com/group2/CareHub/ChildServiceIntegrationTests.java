package com.group2.CareHub;

import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.child.rest.ChildRequestBody;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.exception.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ChildServiceIntegrationTests {

    @Autowired
    private ChildService childService;

    @Test
    void testChildRegistration() {
        ChildRequestBody request = ChildRequestBody.builder()
                .name("Child")
                .dateOfBirth(LocalDate.now())
                .medicalInformation("Medical Information")
                .address("Address")
                .build();

        ResponseBody response = childService.registerChildWithGuardian(request, 1000);
        assert(response.statusCode() == 200);

        ChildEntity childEntity = childService.getChildByChildId(1);
        assert(childEntity.getChildId() == 1);
    }

    @Test
    void getNullChildEntityTest() {
        try {
            childService.getChildByChildId(1000);
            assert(false);
        } catch (EntityNotFoundException e) {
            // Means exception was caught successfully, so the test passes
            assert(true);
        }
    }
}
