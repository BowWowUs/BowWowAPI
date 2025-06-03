package com.pets.bowwow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pets.bowwow.global.connect.PublicDataPortalConnect;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class BowwowApplicationTests {

	@Autowired
	public PublicDataPortalConnect portalConnect;

	@Test
	void contextLoads() {
		
	}

}
