package com.pets.bowwow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.pets.bowwow.global.connect.PublicDataPortalConnect;
import com.pets.bowwow.global.connect.model.AreaBasedModel;
import com.pets.bowwow.global.connect.model.PublicDataPortalRS;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class BowwowApplicationTests {

	@Autowired
	public PublicDataPortalConnect portalConnect;

	@Test
	void contextLoads() {
		ResponseEntity<PublicDataPortalRS<AreaBasedModel>> aa = portalConnect.getAreaBasedList(1, 1);

		log.info("DATA :: {}", aa.getBody());
	}

}
