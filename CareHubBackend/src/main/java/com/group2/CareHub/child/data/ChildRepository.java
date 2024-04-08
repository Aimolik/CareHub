package com.group2.CareHub.child.data;

import com.group2.CareHub.attendance.data.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<ChildEntity, Integer>{

    List<ChildEntity> findChildEntitiesByGuardianId(int guardianId);

    Optional<ChildEntity> findChildEntityByChildId(int childId);

    List<ChildEntity> findChildEntitiesByGuardianIdAndAttendanceStatus(int guardianId, AttendanceStatus attendanceStatus);
}
