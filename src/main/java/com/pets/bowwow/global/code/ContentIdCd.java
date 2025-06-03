package com.pets.bowwow.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentIdCd {

    _12("관광지"),
    _14("문화시설"),
    _15("축제공연행사"),
    _25("여행코스"),
    _28("레포츠"),
    _32("숙박"),
    _38("쇼핑"),
    _39("음식점"),
    ;

    private final String value;

}
