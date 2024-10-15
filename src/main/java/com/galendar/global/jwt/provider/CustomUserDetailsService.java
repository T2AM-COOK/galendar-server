package com.galendar.global.jwt.provider;

import com.galendar.domain.auth.entity.UserEntity;
import com.galendar.domain.auth.repository.UserRepository;
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

        UserEntity userData = userRepository.findByUserName(userName);

        if (userData != null)
        {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}