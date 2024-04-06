package com.group2.CareHub.staff;

import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.exception.exceptions.EntityNotFoundException;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import com.group2.CareHub.staff.data.StaffEntity;
import com.group2.CareHub.staff.data.StaffRepository;
import com.group2.CareHub.staff.dto.Staff;
import com.group2.CareHub.staff.rest.StaffRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StaffService {

    private final StaffDataMapper staffDataMapper;
    private final StaffRepository staffRepository;

    public StaffService(StaffDataMapper staffDataMapper, StaffRepository staffRepository) {
        this.staffDataMapper = staffDataMapper;
        this.staffRepository = staffRepository;
    }


    public ResponseBody registerStaffAccount(StaffRequestBody staffRequestBody) {
        if(staffRepository.findByEmail(staffRequestBody.getEmail()) != null) {
            return new ResponseBody(400, "Staff account with email: " + staffRequestBody.getEmail() + " already exists!");
        }
        log.info("Creating staff account for email: {}", staffRequestBody.getEmail());
        StaffEntity staffEntity = staffRepository.save(staffDataMapper.staffRequestBodyToEntity(staffRequestBody));
        if(staffEntity == null) {
            log.error("Failure in saving staff account of email: {}", staffEntity.getEmail());
            throw new EntityPersistException("Failure in saving staff account for email: " + staffEntity.getEmail());
        }
        log.info("Successfully saved staff account of email: {}", staffRequestBody.getEmail());
        return new ResponseBody(200, "Successfully saved staff account of email: " + staffEntity.getEmail());
    }

    public Staff getStaffById(int staffId) {
        Optional<StaffEntity> response = staffRepository.findByStaffId(staffId);
        if(response.isEmpty()) {
            log.error("Staff with id: {} not found!", staffId);
            throw new EntityNotFoundException("Staff with id: " + staffId + " not found!");
        }
        return staffDataMapper.staffEntityToStaff(response.get());
    }
}
