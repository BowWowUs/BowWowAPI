package com.pets.bowwow.global.jpa.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PetTemperament {

    FRIENDLY("친근함"),
    GENTLE("온순함"),
    INDEPENDENT("독립적"),
    PLAYFUL("장난기 많음"),
    SHY("수줍음"),
    ACTIVE("활발함"),
    ;

    private final String value;

}
