package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.authentication.FormAuthConfig;

public class AuthApis3 {
	
	//basic auth with preemptive api
	@Test
	public void basic_auth_Preemptive_api_Test(){
		
		given().log().all()
		 .auth()
		  .preemptive()
		  .basic("admin", "admin")
		.when().log().all()
		 .get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
		 .assertThat()
		  .statusCode(200);
	}
	
	//digest based authentication
	
	@Test
	public void basic_auth_digest_api_test(){
		given().log().all()
		 .auth()
		  .digest("admin", "admin")
		 .when().log().all()
		  .get("https://the-internet.herokuapp.com/basic_auth")
		 .then().log().all()
		  .assertThat()
		   .statusCode(200);
		
	}
	
	@Test
	public void basic_auth_form_api_test(){
		given().log().all()
		 .auth()
		  .form("admin","admin", new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm", "username", "password"))    
		.when().log().all()
		 .get("https://classic.freecrm.com/system/authenticate.cfm")
		.then().log().all()
		 .assertThat()
		  .statusCode(200);
		 
		  
	}
	

}
