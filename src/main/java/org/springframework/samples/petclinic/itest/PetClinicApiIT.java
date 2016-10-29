package org.springframework.samples.petclinic.itest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class PetClinicApiIT {
	
	@BeforeClass
	public static void setupRestClient() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/api";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@AfterClass
	public static void resetRestClient() {
		RestAssured.reset();
	}
	
	@Test
	public void loadPettypes() throws Exception {
		get("/pettypes").then().body("$", hasSize(6));
	}
	
	@Test 
	public void loadOwner() {
		get("/owner/1").then().body("id", equalTo(1));
	}
}
