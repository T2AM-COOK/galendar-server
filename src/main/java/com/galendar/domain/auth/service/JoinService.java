package com.galendar.domain.auth.service;

import com.galendar.domain.auth.dto.JoinDto;
import com.galendar.domain.auth.entity.UserEntity;
import com.galendar.domain.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDto joinDto){
        String userName = joinDto.getUserName();
        String passWord = joinDto.getPassWord();

        Boolean isExist = userRepository.existsByUserName(userName);

        if (userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        UserEntity data = new UserEntity();

        data.setUserName(userName);
        data.setPassWord(bCryptPasswordEncoder.encode(passWord));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}