package com.group2.CareHub.attendance.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {

    Optional<AttendanceEntity> findAttendanceEntityByChildIdAndAttendanceStatus(int childId, AttendanceStatus attendanceStatus);
}
