package com.galendar.domain.user.service;

import com.galendar.domain.user.dto.response.UserProfileResponse;
import com.galendar.domain.user.mapper.UserMapper;
import com.galendar.global.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserSecurity userSecurity;
    private final UserMapper userMapper;

    public UserProfileResponse getProfile() {
        return userMapper.toProfileResponse(userSecurity.getUser());
    }

}
