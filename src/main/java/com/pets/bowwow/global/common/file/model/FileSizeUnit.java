package com.pets.bowwow.global.common.file.model;

import lombok.Getter;

@Getter
public enum FileSizeUnit {

    KB("KB")
    
    ;
    private String value;

    private FileSizeUnit(String value){
        this.value = value;
    }
}
