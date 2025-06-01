package com.pets.bowwow.domain.account.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pets.bowwow.global.jpa.constant.GenderCd;

import lombok.Data;

@Data
public class UpdateUserRQ {

    private String nickname;

    private GenderCd genderCd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate brthDt;

    private String bio;

}
