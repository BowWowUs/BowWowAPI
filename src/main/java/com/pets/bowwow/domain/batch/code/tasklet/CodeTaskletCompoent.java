package com.pets.bowwow.domain.batch.code.tasklet;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pets.bowwow.global.jpa.entity.CodeEntity;
import com.pets.bowwow.global.jpa.repository.CodeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CodeTaskletCompoent {

    private final CodeRepository codeRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(List<CodeEntity> codeEntities, CodeEntity code){
        codeEntities.add(code);

        if(codeEntities.size() > 30){
            codeRepository.saveAll(codeEntities);
            codeEntities.clear();
        }
    }

}
