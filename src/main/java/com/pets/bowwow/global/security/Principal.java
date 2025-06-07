package com.pets.bowwow.global.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 인가된 정보 유저정보 조회
 */
public class Principal {

    public static String getUser(){
        String username = null;
		Object principal = null;
		try {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			
		}
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = null;
		}
		return username;
    }

	public static Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
    
}
