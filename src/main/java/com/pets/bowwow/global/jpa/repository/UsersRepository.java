package com.pets.bowwow.global.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pets.bowwow.global.jpa.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, String>{

    Optional<UsersEntity> findByUsernameAndDelYn(String username, String delYn);

}
