package com.group2.CareHub.attendance;

import com.group2.CareHub.attendance.data.AttendanceEntity;
import com.group2.CareHub.attendance.data.AttendanceRepository;
import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public AttendanceEntity createAttendanceRecord(int childId, AttendanceStatus attendanceStatus) {
        AttendanceEntity attendanceEntity = AttendanceEntity.builder()
                .attendanceStatus(attendanceStatus)
                .checkedInTime(LocalDateTime.now())
                .childId(childId)
                .build();
        return saveAttendanceRecord(attendanceEntity);
    }

    public AttendanceEntity saveAttendanceRecord(AttendanceEntity attendanceEntity) {
        AttendanceEntity response = attendanceRepository.save(attendanceEntity);
        if(response == null) {
            log.error("Failed to save attendance record for child with id {}", attendanceEntity.getChildId());
            throw new EntityPersistException("Failed to save attendance record");
        }
        log.info("Attendance record saved for child with id {} and attendance status: {}", attendanceEntity.getChildId(), attendanceEntity.getAttendanceStatus());
        return response;
    }

    public Optional<AttendanceEntity> getAttendanceRecord(int childId, AttendanceStatus attendanceStatus) {
        return attendanceRepository.findAttendanceEntityByChildIdAndAttendanceStatus(childId, attendanceStatus);
    }
}
