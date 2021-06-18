package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetNonBDDApi {
	
	//in non BDD first prepare the request
	//then hit the api
	//then get the response
	//fetch the values from response
	
	@Test
	public void getUser_Non_Bdd_Test(){
		RestAssured.baseURI ="https://gorest.co.in";
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		JsonPath js = response.jsonPath();
		System.out.println(js.getString("meta"));
		System.out.println(js.getString("meta.pages"));
		Assert.assertEquals(js.getString("meta.pages"), 90);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "ngnix");
		
	}
	
	@Test
	public void getUser_Non_Bdd_QueryParam_Test(){
		RestAssured.baseURI ="https://gorest.co.in";
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072");
		request.queryParam("name", "Bhramar Mehrotra");
		request.queryParam("gender", "Female");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "ngnix");
	}
	
	@Test
	public void getUser_Non_Bdd_HashMap_QueryParam_Test(){
		RestAssured.baseURI ="https://gorest.co.in";
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Bhramar Mehrotra");
		params.put("gender", "Female");
		
		request.queryParams(params);
		
		
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));	
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "ngnix");
	}

}
