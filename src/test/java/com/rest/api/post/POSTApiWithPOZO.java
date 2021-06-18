package com.rest.api.post;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class POSTApiWithPOZO {

	@Test
	public void createUser_With_Pojo_Test(){
		
		User user = new User("Nish t", "nisho@test.com", "Female", "Active");
		
		//convert java pojo to json
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userJson);
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		given().log().all()
		 .contentType(ContentType.JSON)
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		 .body(userJson)
		.when().log().all()
		 .post("/public-api/users")
		.then().log().all()
		 .assertThat()
		  .contentType(ContentType.JSON)
		  .and()
		   .body("data.name", equalTo(user.getName()))
		   .body("data.gender", equalTo(user.getGender()));
		  
		
		
	}
	
	
	
}
