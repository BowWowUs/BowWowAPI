package com.pets.bowwow.global.connect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pets.bowwow.global.connect.model.PublicDataPortalRS;
import com.pets.bowwow.global.connect.model.ServiceCategoryModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PublicDataPortalConnect {

    @Value("${public.data.api-key}")
    private String SERVICE_KEY;

    private final PublicDataPotalComponent publicDataPotalComponent;

    public ResponseEntity<PublicDataPortalRS<ServiceCategoryModel>> getCategoryCode(String cat){
        if(cat == null){
            cat = "INIT";
        }
        return switch (cat.length()) {
            case 3 -> publicDataPotalComponent.getCategoryCode(
                    SERVICE_KEY, 
                    "ETC", 
                    "DANG_DANG_US", 
                    "json", 
                    cat, 
                    null, 
                    null
                );
            case 5 -> publicDataPotalComponent.getCategoryCode(
                    SERVICE_KEY, 
                    "ETC", 
                    "DANG_DANG_US", 
                    "json", 
                    cat.substring(0, 3), 
                    cat, 
                    null
                );
            case 9 -> publicDataPotalComponent.getCategoryCode(
                    SERVICE_KEY, 
                    "ETC", 
                    "DANG_DANG_US", 
                    "json", 
                    cat.substring(0, 3), 
                    cat.substring(0, 5), 
                    cat
                );

            default -> publicDataPotalComponent.getCategoryCode(
                    SERVICE_KEY, 
                    "ETC", 
                    "DANG_DANG_US", 
                    "json", 
                    null, 
                    null, 
                    null
                );
        };
    }

    


}
