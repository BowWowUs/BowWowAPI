package com.pets.bowwow.domain.account.model;

import lombok.Data;

@Data
public class AppLoginRS {

    private String grantType;

    private String accessToken;

    private String refreshToken;
    
}
