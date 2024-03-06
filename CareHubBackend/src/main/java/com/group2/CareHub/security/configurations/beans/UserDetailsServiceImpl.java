package com.group2.CareHub.security.configurations.beans;

import com.group2.CareHub.guardian.data.GuardianEntity;
import com.group2.CareHub.guardian.data.GuardianRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final GuardianRepository guardianRepository;

    public UserDetailsServiceImpl(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GuardianEntity guardianEntity = guardianRepository.findByEmail(email);
        return new GuardianDetails(guardianEntity.getEmail(), guardianEntity.getPassword());
    }
}
