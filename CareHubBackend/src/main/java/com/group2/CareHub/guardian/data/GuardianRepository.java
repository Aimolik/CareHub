package com.group2.CareHub.guardian.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardianRepository extends JpaRepository<GuardianEntity, Integer> {

    GuardianEntity findByEmail(String email);

    Optional<GuardianEntity> findByGuardianId(int guardianId);
}
