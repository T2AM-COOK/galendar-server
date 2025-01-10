package com.galendar.global.security;

import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.exception.UserNotFoundException;
import com.galendar.domain.user.mapper.UserMapper;
import com.galendar.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).map(userMapper::toDTO).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        return CustomUserDetails.create(user);
    }
}