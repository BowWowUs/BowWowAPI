package com.pets.bowwow.domain.account.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pets.bowwow.domain.account.model.AppLoginRQ;
import com.pets.bowwow.domain.account.model.PlatformConstant;
import com.pets.bowwow.domain.account.model.RefreshRQ;
import com.pets.bowwow.domain.account.model.UpdateUserRQ;
import com.pets.bowwow.domain.account.model.UserType;
import com.pets.bowwow.global.exception.AppException;
import com.pets.bowwow.global.exception.ExceptionCode;
import com.pets.bowwow.global.jpa.entity.AuthoritiesEntity;
import com.pets.bowwow.global.jpa.entity.AuthoritiesEntity.AuthorityId;
import com.pets.bowwow.global.jpa.entity.UsersEntity;
import com.pets.bowwow.global.jpa.repository.UsersRepository;
import com.pets.bowwow.global.security.Principal;
import com.pets.bowwow.global.security.jwt.JwtTokenProvider;
import com.pets.bowwow.global.security.jwt.model.TokenModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    
    @Transactional
    public TokenModel snsLogin(AppLoginRQ rq) {

        String platform = rq.getPlatform().replace(" ", "");
        PlatformConstant.platformValid(platform);
        rq.setId(platform + "_" + rq.getId());

        UsersEntity users = this.oAuth2RegisterUsers(rq);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(users.getUsername(), "", users.getAuthorities());

        TokenModel result = jwtTokenProvider.createToken(usernamePasswordAuthenticationToken);
        
        return result;
    }

    private UsersEntity oAuth2RegisterUsers(AppLoginRQ rq) throws AppException{
        
        String username = rq.getId();
        Optional<UsersEntity> userWrapper = usersRepository.findById(username);

        if(userWrapper.isPresent()) {
            UsersEntity usersEntity = userWrapper.get();            

            if("Y".equals(usersEntity.getDelYn())){

                LocalDateTime deleteTime = usersEntity.getUpdatedAt();
                LocalDateTime now = LocalDateTime.now().minusDays(7);

                if(!deleteTime.isBefore(now)){
                    // User의 DeleteDT가 채워져 있고, 7일이 지나지 않았으면 로그인 X 
                    throw new AppException(ExceptionCode.USER_DELETE);     
                }
            }

            return usersEntity;
        }

        // Users 테이블 Insert
        UsersEntity users = new UsersEntity();
        users.setUsername(username);
        users.setPassword(passwordEncoder.encode(rq.getPlatform() + "_" + System.currentTimeMillis()));
        users.setEnabled(true);
        users.setCreatedAt(LocalDateTime.now());
        users.setCreatedBy(username);
        users.setUpdatedAt(LocalDateTime.now());
        users.setUpdatedBy(username);

        usersRepository.save(users);

        // Users의 권한 Authorities 테이블 Insert
        AuthoritiesEntity authorities = new AuthoritiesEntity();
        AuthorityId id = new AuthorityId();
        id.setUsername(username);
        id.setAuthority(UserType.USER.getValue());
        authorities.setId(id);

        users.setAuthorities(authorities);

        return usersRepository.save(users);
    }

    @Transactional
    public void updateUser(UpdateUserRQ rq) {
        String userId = Principal.getUser();

        if("anonymousUser".equals(userId)){
            throw new AppException(ExceptionCode.NOT_AUTHORIZED_USER);
        }

        UsersEntity users = usersRepository.findByUsernameAndDelYn(userId, "N")
            .orElseThrow(() -> new AppException(ExceptionCode.DATA_NOT_FIND, "사용자를 찾을수 없습니다."));

        if(rq.getNickname() != null && !rq.getNickname().isBlank()){
            users.setNickname(rq.getNickname());
        }

        if(rq.getGenderCd() != null){
            users.setGenderCd(rq.getGenderCd());
        }        

        if(rq.getBrthDt() != null){
            users.setBrthDt(rq.getBrthDt());
        }

        if(rq.getBio() != null && !rq.getBio().isBlank()){
            users.setBio(rq.getBio());
        }

        

    }

    public TokenModel tokenRefresh(RefreshRQ rq) {

        String refreshToken = rq.getRefreshToken();
        jwtTokenProvider.validateRefreshToken(refreshToken);

        String userId = jwtTokenProvider.getRefreshSubject(refreshToken);

        UsersEntity user = usersRepository.findByUsernameAndDelYn(userId, "N")
            .orElseThrow(() -> new AppException(ExceptionCode.DATA_NOT_FIND, "로그인을 다시해주세요."));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

        TokenModel result = jwtTokenProvider.createToken(authentication); 
        result.setRefreshToken(null);

        return result;
    }



}
