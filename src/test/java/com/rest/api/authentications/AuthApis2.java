package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthApis2 {
	
	@Test
	public void checkOAuth2_APITest(){
		
		
		RequestSpecification request = RestAssured
		 .given().log().all()
		   .auth()
		    .oauth2("c9bfce61eee05cfd4848d4cce3778d49c154f7c6");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/api/1920/chickens-feed");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	//1. generate the token at teh run time by using authentication token api
	//2. Maintain a String tokenID from this response
	//3. hit the next api with this tokenID
	
	@Test
	public void getAuthTokenAPITest(){
		//1. generate the token at teh run time by using authentication token api
		RequestSpecification request = RestAssured
		 .given()
		  .formParam("client_id", "API_Basics_Coop")
		  .formParam("client_secret", "9f2c4bc79569738a30a9d5cb67d9c583")
		  .formParam("grant_type","client_credentials");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/token");
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		//2. Maintain a String tokenID from this response
		String tokenID = response.jsonPath().getString("access_token");
		System.out.println("API token id is: "+tokenID);
		
		//second api- feeding the chcken api
		RequestSpecification request1 = RestAssured
				 .given().log().all()
				   .auth()
				    .oauth2(tokenID);
				
				Response response1 = request1.post("http://coop.apps.symfonycasts.com/api/1920/chickens-feed");
				
				System.out.println(response1.getStatusCode());
				System.out.println(response1.prettyPrint());
		
	}
	
	
	

}
