package com.k.garlander.service;

import com.k.garlander.dto.CustomUserDetails;
import com.k.garlander.entity.UserEntity;
import com.k.garlander.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByEmail(email);

        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        throw new UsernameNotFoundException("User not found");
    }
}
