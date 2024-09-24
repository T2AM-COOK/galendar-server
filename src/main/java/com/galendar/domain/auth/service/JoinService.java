package com.k.garlander.service;

import com.k.garlander.dto.JoinDTO;
import com.k.garlander.entity.UserEntity;
import com.k.garlander.repository.UserRepository;
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

    public void joinProcess(JoinDTO joinDto){
        String userName = joinDto.getUserName();
        String passWord = joinDto.getPassWord();

        Boolean isExist = userRepository.existsByUserName(userName);

        if (userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        UserEntity data = new UserEntity();

        data.setUserName(userName);
        data.setPassword(bCryptPasswordEncoder.encode(passWord));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}