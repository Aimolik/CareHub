package com.group2.CareHub.security;

import com.group2.CareHub.account.AccountDetails;
import com.group2.CareHub.common.Role;
import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.data.GuardianRepository;
import com.group2.CareHub.staff.data.StaffEntity;
import com.group2.CareHub.staff.data.StaffRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final GuardianRepository guardianRepository;
    private final StaffRepository staffRepository;

    public UserDetailsService(GuardianRepository guardianRepository, StaffRepository staffRepository) {
        this.guardianRepository = guardianRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAndRole) throws UsernameNotFoundException {
        String[] split = emailAndRole.split("#");
        String email = split[0];
        String role = split[1];

        if (role.equals("GUARDIAN")) {
            GuardianEntity guardianEntity = guardianRepository.findByEmail(email);
            return new AccountDetails(guardianEntity.getGuardianId(), guardianEntity.getEmail(), guardianEntity.getPassword(), Role.GUARDIAN);
        } else {
            StaffEntity staffEntity = staffRepository.findByEmail(email);
            return new AccountDetails(staffEntity.getStaffId(), staffEntity.getEmail(), staffEntity.getPassword(), Role.STAFF);
        }
    }
}
