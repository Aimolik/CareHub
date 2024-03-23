package com.group2.CareHub.attendance.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
}
