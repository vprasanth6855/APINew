package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth1APITest {
	
	@Test
	public void TwitterStatusAPI_OAth1_Test(){
		RequestSpecification request = RestAssured.given()
		 .auth()
		  .oauth("...", "...", "...", "...");
		
		Response response = request.post("https://api.twitter.com/1.1/statuses/update.json?status=Hey this is my tweet from rest assured code!!");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		
	}
	

}
