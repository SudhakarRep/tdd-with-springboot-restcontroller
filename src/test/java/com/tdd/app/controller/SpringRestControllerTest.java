package com.tdd.app.controller;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.tdd.app.Application;

@RunWith(SpringJUnit4ClassRunner.class)   // 1
@ContextConfiguration(classes = Application.class)  // 2
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringRestControllerTest {
	
	@Value("${server.port}")   // 6
    int port;

	@Before
	public void setBaseUri () {
		 RestAssured.port = port;
		 RestAssured.baseURI = "http://localhost"; // replace as appropriate
	}
	
	@Test
	public void getDataTest() {
		get("/api/tdd/paramTestData").then().assertThat().body("data", equalTo("paramTestData"));
	}

}
