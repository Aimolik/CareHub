package com.group2.CareHub.child;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.child.data.ChildRepository;
import com.group2.CareHub.child.rest.ChildRequestBody;
import com.group2.CareHub.common.ResponseBody;
import com.group2.CareHub.common.enumeration.Role;
import com.group2.CareHub.exception.exceptions.EntityNotFoundException;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public ResponseBody registerChildWithGuardian(ChildRequestBody childRequestBody, int guardianId) {
        log.info("Creating child for guardian id {}", guardianId);
        saveChild(childRequestBodyToEntity(childRequestBody, guardianId));
        log.info("Successfully saved child for guardian id: {}", guardianId);
        return new ResponseBody(200, "Successfully saved child for guardian id: " + guardianId);
    }

    public List<ChildEntity> getChildrenByGuardianId(int guardianId) {
        return childRepository.findChildEntitiesByGuardianId(guardianId);
    }

    public void saveChild(ChildEntity childEntity) {
        ChildEntity response = childRepository.save(childEntity);
        if(response == null) {
            log.error("Failure in saving child entity for guardian id: {}", childEntity.getGuardianId());
            throw new EntityPersistException("Failure in saving child entity for guardian id: " + childEntity.getGuardianId());
        }
    }

    public ChildEntity getChildByChildId(int childId) {
        Optional<ChildEntity> childEntity = childRepository.findChildEntityByChildId(childId);
        if(childEntity.isEmpty()) {
            log.error("Child with id {} not found", childId);
            throw new EntityNotFoundException("Child with id " + childId + " not found");
        }
        return childEntity.get();
    }

    public List<ChildEntity> getChildrenByGuardianIdAndAttendanceStatus(int guardianId, AttendanceStatus attendanceStatus) {
        return childRepository.findChildEntitiesByGuardianIdAndAttendanceStatus(guardianId, attendanceStatus);
    }

    public ResponseBody deleteChild(int childId, AccountDetails accountDetails) {
        ChildEntity childEntity = getChildByChildId(childId);
        if(accountDetails.getRole() == Role.STAFF || (accountDetails.getRole() == Role.GUARDIAN && childEntity.getGuardianId() == accountDetails.getId())) {
            childRepository.delete(childEntity);
            return new ResponseBody(200, "Child with id " + childId + " has been deleted");
        } else {
            return new ResponseBody(403, "You do not have permission to delete this child, either you are not a staff, or this is not your child!");
        }
    }

    private ChildEntity childRequestBodyToEntity(ChildRequestBody childRequestBody, int guardianId) {
        return ChildEntity.builder()
                .guardianId(guardianId)
                .name(childRequestBody.getName())
                .dateOfBirth(childRequestBody.getDateOfBirth())
                .address(childRequestBody.getAddress())
                .medicalInformation(childRequestBody.getMedicalInformation())
                .attendanceStatus(AttendanceStatus.CHECKED_OUT)
                .build();
    }
}
