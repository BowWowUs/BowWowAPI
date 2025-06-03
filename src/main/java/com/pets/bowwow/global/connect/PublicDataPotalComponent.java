package com.pets.bowwow.global.connect;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.pets.bowwow.global.connect.model.PublicDataPortalRS;
import com.pets.bowwow.global.connect.model.ServiceCategoryModel;

@Component
@HttpExchange
interface PublicDataPotalComponent {

    @GetExchange("/categoryCode")
    ResponseEntity<PublicDataPortalRS<ServiceCategoryModel>> getCategoryCode(
        @RequestParam String serviceKey,
        @RequestParam String MobileOS,
        @RequestParam String MobileApp,
        @RequestParam String _type,
        @RequestParam(required = false) String cat1,
        @RequestParam(required = false) String cat2,
        @RequestParam(required = false) String cat3
    );

}
