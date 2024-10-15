package com.k.garlander.service;

import com.k.garlander.dto.CustomUserDetails;
import com.k.garlander.entity.UserEntity;
import com.k.garlander.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByEmail(userName);

        if (userData != null)
        {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}