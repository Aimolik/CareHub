package com.group2.CareHub.staff.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

    StaffEntity findByEmail(String email);
}
