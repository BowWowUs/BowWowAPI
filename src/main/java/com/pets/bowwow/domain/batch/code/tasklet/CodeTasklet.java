package com.pets.bowwow.domain.batch.code.tasklet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.pets.bowwow.global.code.ServiceCategoryCd;
import com.pets.bowwow.global.connect.PublicDataPortalConnect;
import com.pets.bowwow.global.connect.model.PublicDataPortalRS;
import com.pets.bowwow.global.connect.model.ServiceCategoryModel;
import com.pets.bowwow.global.jpa.entity.CodeEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CodeTasklet implements Tasklet{
    
    private final PublicDataPortalConnect portalConnect;
    private final CodeTaskletCompoent codeTaskletCompoent;
    private final List<CodeEntity> codeEntities = new ArrayList<>();
    
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        add("INIT");
        return RepeatStatus.FINISHED;
    }

    private void add(String cat){
        if(cat == null){
            return;
        }
        PublicDataPortalRS<ServiceCategoryModel> rs = portalConnect.getCategoryCode(cat).getBody();
        List<ServiceCategoryModel> items = rs.getResponse().getBody().getItems().getItem();

        for(ServiceCategoryModel item : items){
            if(cat.equals(item.getCode())){
                continue;
            }

            CodeEntity code = CodeEntity.builder()
                .cd(item.getCode())
                .name(item.getName())
                .build();
            
            code.setCreatedBy("BATCH_SYSTEM");
            code.setCreatedAt(LocalDateTime.now());
            code.setUpdatedBy("BATCH_SYSTEM");
            code.setUpdatedAt(LocalDateTime.now());
            
            codeTaskletCompoent.add(codeEntities, code);

            add(item.getCode());
        }

    }

}
