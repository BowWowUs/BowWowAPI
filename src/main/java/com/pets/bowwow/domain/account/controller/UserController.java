package com.pets.bowwow.domain.account.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets.bowwow.domain.account.model.AppLoginRQ;
import com.pets.bowwow.domain.account.model.RefreshRQ;
import com.pets.bowwow.domain.account.model.UpdateUserRQ;
import com.pets.bowwow.domain.account.service.UserService;
import com.pets.bowwow.global.security.jwt.model.TokenModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sns/login")
    public ResponseEntity<TokenModel> snsLogin(@Valid @RequestBody AppLoginRQ rq){

        TokenModel result = userService.snsLogin(rq);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserRQ rq){

        userService.updateUser(rq);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenModel> tokenRefresh(@Valid @RequestBody RefreshRQ rq){

        TokenModel result = userService.tokenRefresh(rq);

        return ResponseEntity.ok().body(result);
    }
    
}
