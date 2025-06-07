package com.pets.bowwow.global.jpa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pets.bowwow.global.base.BaseEntity;
import com.pets.bowwow.global.jpa.constant.GenderCd;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Comment("유저 테이블")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersEntity extends BaseEntity implements UserDetails{

    @Id
    @Column(name = "USERNAME", nullable = false)
    @Comment("유저 ID or 유저 식별자")
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(name = "NICKNAME")
    @Comment("닉네임")
    private String nickname;

    @Column(name = "GENDER_CD")
    @Enumerated(EnumType.STRING)
    @Comment("성별코드")
    private GenderCd genderCd;

    @Column(name = "BIO", columnDefinition = "TEXT")
    @Comment("자기소개")
    private String bio;

    @Column(name = "BRTH_DT")
    @Comment("생년월일")
    private LocalDate brthDt;

    @Column(name = "ENABLED", nullable = false)
    @Comment("회원 활성화")
    private Boolean enabled;

    @Column(name = "FILE_GROUP_NO")
    @Comment("파일 그룹번호")
    private Long fileGroupNo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    private Set<AuthoritiesEntity> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(AuthoritiesEntity authorities){
        if(this.authorities == null){
            this.authorities = new HashSet<>();
        }

        this.authorities.add(authorities);
    }

    

}
