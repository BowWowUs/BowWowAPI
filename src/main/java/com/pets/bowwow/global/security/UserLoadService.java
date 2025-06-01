package com.pets.bowwow.global.security;

import com.pets.bowwow.global.jpa.entity.UsersEntity;
import com.pets.bowwow.global.jpa.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoadService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<UsersEntity> usersWrapper = usersRepository.findByUsernameAndDelYn(userId, "N");

        if(!usersWrapper.isPresent()){
            throw new UsernameNotFoundException(FailType.USER_NOT_FOUNT.getValue());
        }

        UsersEntity userAccount = usersWrapper.get();

         if(!userAccount.isEnabled()){
            throw new BadCredentialsException(FailType.ACOUNT_DISABLE.getValue());
        }
       
        if(!userAccount.isAccountNonExpired()){
            throw new BadCredentialsException(FailType.ACOUNT_EXPRIED.getValue());
        }

        if(!userAccount.isAccountNonLocked()){
            throw new BadCredentialsException(FailType.ACOUNT_LOCK.getValue());
        }

        if(userAccount.getDelYn().equals("Y")){
            throw new BadCredentialsException(FailType.USER_DELETE.getValue());
        }

        userAccount.getAuthorities();
        return userAccount;
    }

}