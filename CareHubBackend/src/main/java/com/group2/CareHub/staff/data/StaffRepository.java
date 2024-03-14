package com.group2.CareHub.staff.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

    StaffEntity findByEmail(String email);

    Optional<StaffEntity> findByStaffId(int staffId);
}
