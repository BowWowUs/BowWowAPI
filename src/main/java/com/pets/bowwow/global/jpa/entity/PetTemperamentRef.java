package com.pets.bowwow.global.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PET_TEMPERAMENT_REF")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetTemperamentRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REF_ID")
    private Long refId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PET_ID", referencedColumnName = "PET_ID", nullable = false)
    private PetEntity petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPERAMENT", referencedColumnName = "TEMPERAMENT", nullable = false)
    private PetTemperamentEntity temperament;

}
