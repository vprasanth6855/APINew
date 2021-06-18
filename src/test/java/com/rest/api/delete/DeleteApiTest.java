package com.rest.api.delete;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class DeleteApiTest {
	
	@Test
	public void delete_user_api_Test(){
		
		User user = new User("Har v", "har3@test.com", "Male", "Active");
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		given().log().all()
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when().log().all()
		 .delete("/public-api/users/"+userId)
		.then().log().all()
		 .assertThat()
		  .contentType(ContentType.JSON)
		 .and()
		  .body("meta",equalTo(null));
		
		given()
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when()
		 .get("/public-api/users/"+userId)
		.then()
		 .assertThat()
		  .body("meta", equalTo(null));
		  
		
		  
		
		
	}

}
