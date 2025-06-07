package com.pets.bowwow.domain.batch.company.tasklet;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pets.bowwow.global.jpa.entity.TravelPlaceEntity;
import com.pets.bowwow.global.jpa.repository.TravelPlaceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyTaskletComponent {

    private final TravelPlaceRepository travelPlaceRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(List<TravelPlaceEntity> entities){
        travelPlaceRepository.saveAll(entities);
    }
}
