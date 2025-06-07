package com.pets.bowwow.global.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pets.bowwow.global.jpa.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long>{

}
