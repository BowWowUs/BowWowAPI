package com.pets.bowwow.global.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {

    private String grantType;

    private String accessToken;

    private String refreshToken;
}
