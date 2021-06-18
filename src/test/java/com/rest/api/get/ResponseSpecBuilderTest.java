package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	

	ResponseSpecBuilder res = new ResponseSpecBuilder();
	ResponseSpecification resSpec_200 = res
			                      .expectContentType(ContentType.JSON)
			                       .expectStatusCode(200)
			                        .expectHeader("Server", "nginx").build();
	
	ResponseSpecification resSpec_400_BADRequest = res
            .expectContentType(ContentType.JSON)
             .expectStatusCode(400)
              .expectHeader("Server", "nginx").build();
	

	ResponseSpecification resSpec_401_Auth_Fail = res
            .expectContentType(ContentType.JSON)
             .expectStatusCode(401)
              .expectHeader("Server", "nginx").build();
	

	@Test
	public void ResponseSpecTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when()
		 .get("/public-api/users")
		.then()
		 .assertThat()
		   .spec(resSpec_200);
	}
	
	@Test
	public void ResponseSpec_Auth_fail_Test(){
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		.when()
		 .get("/public-api/users")
		.then()
		 .assertThat()
		  .spec(resSpec_401_Auth_Fail);
	}

}
