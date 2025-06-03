package com.pets.bowwow.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceCategoryCd {

    A01("자연"),
    A02("인문(문화/예술/역사)"),
    A03("레포츠"),
    A04("쇼핑"),
    A05("음식"),
    B02("숙박"),
    C01("추천코스"),
    ;

    private final String value;
}
