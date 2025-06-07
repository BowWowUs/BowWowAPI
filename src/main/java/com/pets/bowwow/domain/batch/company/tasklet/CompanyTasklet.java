package com.pets.bowwow.domain.batch.company.tasklet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.pets.bowwow.global.connect.PublicDataPortalConnect;
import com.pets.bowwow.global.connect.model.AreaBasedModel;
import com.pets.bowwow.global.connect.model.PublicDataPortalRS;
import com.pets.bowwow.global.jpa.entity.TravelPlaceEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyTasklet implements Tasklet{

    private final PublicDataPortalConnect portalConnect;
    private final CompanyTaskletComponent companyTaskletComponent;

    private final Integer ROW = 100;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Integer page = 1;
        PublicDataPortalRS<AreaBasedModel> rs = portalConnect.getAreaBasedList(100, page++).getBody();
        Integer roof = (rs.getResponse().getBody().getTotalCount() / ROW) + 1;

        List<AreaBasedModel> items = rs.getResponse().getBody().getItems().getItem();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        do{
            if(items.isEmpty()){
                items = portalConnect.getAreaBasedList(ROW, page++).getBody().getResponse().getBody().getItems().getItem();
            }

            List<TravelPlaceEntity> entities = new ArrayList<>();

            entities.addAll(
                items.stream()
                    .map(item -> {
                        TravelPlaceEntity entity = TravelPlaceEntity.builder()
                            .contentId(item.getContentid())
                            .addr1(item.getAddr1())
                            .addr2(item.getAddr2())
                            .areaCode(item.getAreacode())
                            .cat1(item.getCat1())
                            .cat2(item.getCat2())
                            .cat3(item.getCat3())
                            .contentTypeId(item.getContenttypeid())
                            .firstImage(item.getFirstimage())
                            .firstImage2(item.getFirstimage2())
                            .cpyrhtDivCd(item.getCpyrhtDivCd())
                            .lon(item.getMapx())
                            .lat(item.getMapy())
                            .mLevel(item.getMlevel())
                            .modifiedTime(LocalDateTime.parse(item.getModifiedtime(), formatter))
                            .createdTime(LocalDateTime.parse(item.getCreatedtime(), formatter))
                            .sigunguCode(item.getSigungucode())
                            .tel(item.getTel())
                            .title(item.getTitle())
                            .zipCode(item.getZipcode())
                            .build();

                        entity.setCreatedBy("BATCH_SYSTEM");
                        entity.setCreatedAt(LocalDateTime.now());
                        entity.setUpdatedBy("BATCH_SYSTEM");
                        entity.setUpdatedAt(LocalDateTime.now());
                        return entity;
                    })
                    .toList()
            );
            
            companyTaskletComponent.add(entities);

            items.clear();
        } while(--roof > 0);

        return RepeatStatus.FINISHED;
    }

}
