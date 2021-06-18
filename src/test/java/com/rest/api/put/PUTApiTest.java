package com.rest.api.put;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PUTApiTest {
	
	@Test
	public void update_User_PUT_API_Test(){
	//create a POST Request with payload
	User user = new User("Uman G", "umy5@test.com", "Male", "Active");
	
	//convert the pozo to json using Jackson- the class responsible is ObjectMapper
	ObjectMapper mapper = new ObjectMapper();
	String userJson = null;
	
	try {
		userJson = mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("POST call JSON is: "+userJson);
	
	//write POST call
	
	RestAssured.baseURI = "https://gorest.co.in";
	
	int userId = given().log().all()
	 .contentType(ContentType.JSON)
	 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
	 .body(userJson)
	.when().log().all()
	 .post("/public-api/users")
	.then().log().all()
	 .assertThat()
	  .contentType(ContentType.JSON)
	  .extract().path("data.id");
	  
	System.out.println("user id is: "+ userId);
	
	
	//call PUT API
	user.setEmail("ums6@test.com");
	user.setGender("Male");
	user.setStatus("Active");
	
	//Convert this POZO to JSON
	String updatedUserJson = null;
	
	try {
		updatedUserJson = mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	given().log().all()
	 .contentType(ContentType.JSON)
	 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
	 .body(updatedUserJson)
	.when().log().all()
	 .put("public-api/users/"+userId)
	.then().log().all()
	 .assertThat()
	  .contentType(ContentType.JSON)
	  .and()
	   .body("data.email",equalTo(user.getEmail()))
	  .and()
	   .body("data.id", equalTo(userId))
	  .and()
	   .body("data.name", equalTo(user.getName()))
	  .and()
	   .body("data.gender", equalTo(user.getGender()))
	  .and()
	   .body("data.status", equalTo(user.getStatus()));
	  
	 //GET call
	
	given().log().all()
	 .contentType(ContentType.JSON)
	 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
	.when().log().all()
	.get("public-api/users/"+userId)
	.then().log().all()
	 .assertThat()
	  .body("data.id", equalTo(userId))
	 .and()
	  .body("data.email",equalTo(user.getEmail()))
	  .and()
	   .body("data.name", equalTo(user.getName()))
	  .and()
	   .body("data.gender", equalTo(user.getGender()))
	  .and()
	   .body("data.status", equalTo(user.getStatus()));
	  
	  
}
	
}
