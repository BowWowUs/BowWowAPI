package com.pets.bowwow.global.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Comment;

import com.pets.bowwow.global.base.BaseEntity;
import com.pets.bowwow.global.jpa.constant.GenderCd;
import com.pets.bowwow.global.jpa.constant.PetSizeCd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PET")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Comment("애완동물")
public class PetEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    @Comment("애완동물 ID")
    private Long petId;

    @Column(name = "NAME", nullable = false)
    @Comment("이름")
    private String name;

    @Column(name = "BREED", nullable = false)
    @Comment("품종")
    private String breed;

    @Column(name = "BRTH_DT")
    @Comment("생년월일")
    private LocalDate brthDt;

    @Column(name = "GENDER_CD", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("성별")
    private GenderCd genderCd;

    @Column(name = "PET_SIZE_CD", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("크기")
    private PetSizeCd petSizeCd;

    @Column(name = "FILE_GROUP_NO")
    private Long fileGroupNo;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petId")
    private List<PetTemperamentRef> refs;


}
