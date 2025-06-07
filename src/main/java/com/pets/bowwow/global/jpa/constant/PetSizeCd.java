package com.pets.bowwow.global.jpa.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PetSizeCd {

    XS("초소형"),
    S("소형"),
    M("중형"),
    L("대형"),
    XL("초대형"),
    ;

    private final String value;

}
