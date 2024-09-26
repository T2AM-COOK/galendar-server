package com.k.garlander.repository;

import com.k.garlander.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByUserName(String userName);

    UserEntity findByUserName(String userName);
}