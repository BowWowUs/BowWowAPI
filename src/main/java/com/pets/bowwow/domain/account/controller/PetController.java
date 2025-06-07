package com.pets.bowwow.domain.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/pet")
public class PetController {

    @PostMapping
    public ResponseEntity<Void> createPet(){

        

        return ResponseEntity.ok().build();
    }

}
