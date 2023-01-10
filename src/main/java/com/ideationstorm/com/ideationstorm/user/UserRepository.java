package com.ideationstorm.com.ideationstorm.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String name);

    Optional<UserEntity> findByEmail(String email);

//    List<UserEntity> findAll();
}
