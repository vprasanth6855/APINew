package com.rest.api.authentications;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;


public class AuthApis {
	
	//basic auth
	//username and pwd
	
	@Test
	public void basic_auth_api_test(){
		given().log().all()
		 .auth()
		 .preemptive()
		  .basic("admin", "admin")
		 .when().log().all()
		  .get(" https://the-internet.herokuapp.com/basic_auth")
		 .then().log().all()
		  .assertThat()
		   .statusCode(200);
	 
	}
	
	//Oauth 2.0
	//passing authorization token
	//if the token is already available with us
	@Test
	public void OAuth2_API_Test(){
		given().log().all()
		 .auth()
		  .oauth2("c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when().log().all()
		 .get("https://gorest.co.in/public-api/users")
		.then().log().all()
		 .assertThat()
		  .statusCode(200);
	}
	
	//OAuth2- when token is already available- given in header format
	@Test
	public void OAuth2_Api_Test_WithAuthHeader(){
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		 .contentType("application/json")
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when()
		 .get("/public-api/users?status=inactive")
		.then()
		 .assertThat()
		  .statusCode(200)
		 .and()
		  .header("Server", "nginx");
	}
	
	@Test
	public void OAuth_API_WithTwoQueryParams_API_Test(){
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
		 .contentType("application/json")
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		 .queryParam("name", "Bas Johar")
		 .queryParam("status", "Inactve")
		.when()
		 .get("/public-api/users")
		.then() 
		 .assertThat()
		  .statusCode(200)
		  .and()
		  .header("Server", "nginx");
		
	}
	
	

}
