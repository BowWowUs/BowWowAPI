package com.pets.bowwow.domain.account.model;


import com.pets.bowwow.global.exception.AppException;
import com.pets.bowwow.global.exception.ExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PlatformConstant{

    NAVER("naver", "네이버"),
    KAKAO("kakao", "카카오"),
    APPLE("apple", "애플")
    ;

    private final String value;

    private final String desc;

    public static void platformValid(String platform){
        for(PlatformConstant constant : PlatformConstant.values()){
            if(constant.getValue().equals(platform)) return;
        }

        throw new AppException(ExceptionCode.NOT_FOUNT_PLATFORM);
    }

}
