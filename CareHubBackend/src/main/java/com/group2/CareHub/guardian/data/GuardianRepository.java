package com.group2.CareHub.guardian.data;

import com.group2.CareHub.guardian.GuardianService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Guard;

@Repository
public interface GuardianRepository extends JpaRepository<GuardianEntity, Integer> {

    GuardianEntity findByEmail(String email);
}
