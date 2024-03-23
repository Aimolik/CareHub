package com.group2.CareHub.checking;

import com.group2.CareHub.attendance.AttendanceService;
import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.checking.rest.CheckingRequestBody;
import com.group2.CareHub.child.ChildService;
import com.group2.CareHub.child.data.ChildEntity;
import com.group2.CareHub.common.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        log.info("Checking in child with id {}", childEntity.getChildId());
        attendanceService.createAttendanceRecord(childEntity.getChildId(), AttendanceStatus.CHECKED_IN);
        return null;
    }

    public ResponseBody checkout(CheckingRequestBody checkingRequestBody) {
        log.info("check out");
        return null;
    }
}
