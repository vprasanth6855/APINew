package com.rest.api.schema;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CheckSchemaTest {
	
	@Test
	public void bookings_Schema_Test(){
		
		given().log().all()
		 .contentType(ContentType.JSON)
		 .body(new File("C:/Users/veena/workspace/APIRevision/sc/test/resources/Bookings.json"))
		 .when().log().all()
		  .post("https://restful-booker.herokuapp.com/booking")
		.then().log().all()
		 .assertThat()
		  .statusCode(200)
		  .and()
		   .body(matchesJsonSchemaInClasspath("BookingSchema.json"));
	}
	
	@Test
	public void ActiveMale_API_Schema_Test(){
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		 .contentType(ContentType.JSON)
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when().log().all()
		 .get("/public-api/users?status=inactive&gender=male")
		.then().log().all()
		 .assertThat()
		  .statusCode(200)
		 .and()
		  .body(matchesJsonSchemaInClasspath("ActiveMaleSchema.json"));
		 
		
	}
	

}
