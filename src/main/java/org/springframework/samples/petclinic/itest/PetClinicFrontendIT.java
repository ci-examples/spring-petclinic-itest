package org.springframework.samples.petclinic.itest;

import static org.hamcrest.Matchers.equalTo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class PetClinicFrontendIT {

	@BeforeClass
	public static void setupRestClient() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@AfterClass
	public static void resetRestClient() {
		RestAssured.reset();
	}

	@Test
	public void index() throws Exception {
		RestAssured.expect().body("html.head.title", equalTo("Spring Boot Petclinic React Example")).when().get("/");
	}

	@Test
	public void allRequestsReturnIndexHtml() throws Exception {
		RestAssured.expect() //
				.body("html.head.title", equalTo("Spring Boot Petclinic React Example")) //
				.and().statusCode(200) //
				.when() //
				.get("/a-page-that-does-not-exists");
	}

}
