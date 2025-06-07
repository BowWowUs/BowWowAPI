package com.pets.bowwow.global.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pets.bowwow.global.jpa.constant.PetTemperament;
import com.pets.bowwow.global.jpa.entity.PetTemperamentEntity;

public interface PetTemperamentRepository extends JpaRepository<PetTemperamentEntity, PetTemperament>{

}
