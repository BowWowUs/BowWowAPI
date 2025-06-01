package com.pets.bowwow.domain.account.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRQ {

    @NotBlank
    private String refreshToken;

}
