package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTApiBDD {

	@Test
	public void tokenPostBDDAPI_JSONSTring_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().log().all()
		 .contentType(ContentType.JSON)
		 .body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
		.when().log().all()
		 .post("/auth")
		.then().log().all()
		 .assertThat()
		  .statusCode(200);
		 
		 
	}
	
	public void tokenPostBDDAPI_FILE_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID = given().log().all()
		 .contentType(ContentType.JSON)
		 .body(new File("C:/Users/veena/workspace/APIRevision/src/test/java/Data/credentials.json"))
		.when().log().all()
		 .post("/auth")
		.then().log().all()
		 .extract().path("token");
		
		System.out.println("The token id is: "+tokenID);
		Assert.assertNotNull(tokenID);
		 
		 
	}
	
	@Test
	public void createUser_Post_API_BDD_String_Test(){
		RestAssured.baseURI = "https://gorest.co.in/";
		
		 given().log().all()
		 .contentType(ContentType.JSON)
		 .header("Authentication", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		 .body("{\"name\": \"ona Dubashi\",\"email\": \"ona_dubashi@me.net\",\"gender\": \"Female\",\"status\": \"Inactive\",\"created_at\": \"2021-05-25T03:50:03.969+05:30\",\"updated_at\": \"2021-05-25T03:50:03.969+05:30\"}")
	     .when().log().all()
	      .post("public-api/users")
	     .then().log().all()
	 	   .assertThat()
	        .body("data.message", equalTo("has already been taken"));
	        		
		 
	}
	
	@Test
	public void createUser_Post_API_BDD_FIle_Test(){
		RestAssured.baseURI = "https://gorest.co.in/";
		
		 given().log().all()
		 .contentType(ContentType.JSON)
		 .header("Authentication", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		 .body(new File("C:/Users/veena/workspace/APIRevision/src/test/java/Data/user.json"))
	     .when().log().all()
	      .post("public-api/users")
	     .then().log().all()
	 	   .assertThat()
	        .body("data.message", equalTo("has already been taken"));
	        		
		 
	}
	
	
}
