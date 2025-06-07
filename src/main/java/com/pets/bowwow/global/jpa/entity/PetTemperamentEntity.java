package com.pets.bowwow.global.jpa.entity;

import org.hibernate.annotations.Comment;

import com.pets.bowwow.global.base.BaseEntity;
import com.pets.bowwow.global.jpa.constant.PetTemperament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PET_TEMPERAMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Comment("애완동물 성향테이블")
public class PetTemperamentEntity extends BaseEntity{

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "TEMPERAMENT", nullable = false)
    @Comment("성향")
    private PetTemperament temperament;

}
