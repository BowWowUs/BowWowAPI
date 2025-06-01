package com.pets.bowwow.domain.account.model;

import com.google.firebase.database.annotations.NotNull;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AppLoginRQ {

    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    private String platform;
}
