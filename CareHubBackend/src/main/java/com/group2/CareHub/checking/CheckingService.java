package com.group2.CareHub.checking;

import com.group2.CareHub.attendance.AttendanceService;
import com.group2.CareHub.attendance.data.AttendanceEntity;
import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.checking.rest.CheckingRequestBody;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.common.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CheckingService {

    private final AttendanceService attendanceService;
    private final ChildService childService;

    public CheckingService(AttendanceService attendanceService, ChildService childService) {
        this.attendanceService = attendanceService;
        this.childService = childService;
    }

    public ResponseBody checkIn(CheckingRequestBody checkingRequestBody) {
        ChildEntity childEntity = childService.getChildByChildId(checkingRequestBody.getChildId());
        if(childEntity.getAttendanceStatus() == AttendanceStatus.CHECKED_IN) {
            return new ResponseBody(400, "Child of id " + childEntity.getChildId() + " is already checked in!");
        }
        log.info("Checking in child with id {}", childEntity.getChildId());
        childEntity.setAttendanceStatus(AttendanceStatus.CHECKED_IN);
        childService.saveChild(childEntity);
        attendanceService.createAttendanceRecord(childEntity.getChildId(), AttendanceStatus.CHECKED_IN);
        return new ResponseBody(200, "Child of id " + childEntity.getChildId() + " has been checked in!");
    }

    public ResponseBody checkout(CheckingRequestBody checkingRequestBody) {
        ChildEntity childEntity = childService.getChildByChildId(checkingRequestBody.getChildId());
        log.info("Checking out child with id {}", childEntity.getChildId());
        if(childEntity.getAttendanceStatus() == AttendanceStatus.CHECKED_OUT) {
            return new ResponseBody(400, "Child of id " + childEntity.getChildId() + " is already checked out!");
        }
        log.info("Checking in child with id {}", childEntity.getChildId());
        childEntity.setAttendanceStatus(AttendanceStatus.CHECKED_OUT);
        childService.saveChild(childEntity);
        attendanceService.createAttendanceRecord(childEntity.getChildId(), AttendanceStatus.CHECKED_OUT);
        return new ResponseBody(200, "Child of id " + childEntity.getChildId() + " has been checked out!");
    }
}
