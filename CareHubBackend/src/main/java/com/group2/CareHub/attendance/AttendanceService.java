package com.group2.CareHub.attendance;

import com.group2.CareHub.attendance.data.AttendanceEntity;
import com.group2.CareHub.attendance.data.AttendanceRepository;
import com.group2.CareHub.attendance.data.AttendanceStatus;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                .createdAt(LocalDateTime.now())
                .childId(childId)
                .build();
        AttendanceEntity response = attendanceRepository.save(attendanceEntity);
        if(response == null) {
            log.error("Failed to create attendance record for child with id {}", childId);
            throw new EntityPersistException("Failed to create attendance record");
        }
        log.info("Attendance record created for child with id {} and attendance status: {}", childId, attendanceStatus);
        return response;
    }
}
