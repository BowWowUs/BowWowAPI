package com.pets.bowwow.domain.account.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile mainImage;

    private List<MultipartFile> subImages;

}
