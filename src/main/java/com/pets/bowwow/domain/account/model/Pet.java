package com.pets.bowwow.domain.account.model;

import java.time.LocalDate;

import com.pets.bowwow.global.jpa.constant.GenderCd;

import lombok.Data;

@Data
public class Pet {

    private String name;

    private String breed;

    private LocalDate brthDt;

    private GenderCd genderCd;

    private String size;



}
