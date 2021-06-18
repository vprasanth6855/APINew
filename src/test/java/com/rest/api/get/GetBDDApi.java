package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetBDDApi {

	@Test
	public void getAPICircuitTest_1(){
		given().log().all()
		 .when().log().all()
		 .get("http://ergast.com/api/f1/2017/circuits.json")
		 .then().log().all()
		  .assertThat()
		  .body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
	}
	
	@Test
	public void getAPICircuitTest_2(){
		Response response = 
		given().log().all()
		 .when().log().all()
		 .get("http://ergast.com/api/f1/2017/circuits.json");
		
		int statusCode = response.getStatusCode();
		System.out.println("api response status code: "+statusCode);
		Assert.assertEquals(statusCode,200);
		
		System.out.println(response.prettyPrint());
		 
		  
	}
	
	@Test
	public void getAPICircuitTest_3(){
		
		RestAssured.baseURI = "http://ergast.com";
		given()
		 .when()
		  .get("/api/f1/2017/circuits.json")
		  .then()
		   .assertThat()
		    .statusCode(200)
		  .and()
		    .contentType(ContentType.JSON)
		  .and()
		    .header("Connection", equalTo("Keep-Alive"));
	}
	
	@Test
	public void getJsonApi_verifyMD5Value(){
		
		String paramValue = "test";
		String expectedMd5Value = "098f6bcd4621d373cade4e832627b4f6";
		
		given().log().all()
		  .param("text", paramValue)
		 .when().log().all()
		  .get("http://md5.jsontest.com")
		 .then().log().all()
		  .assertThat()
		   .body("md5", equalTo(expectedMd5Value));
	}
	
	@DataProvider(name="getCircuitYearData")
	public Object[][] getCircuitYearInfo(){
		
		return new Object[][]{
			{"2017", 20},
			{"2016", 21},
			{"1966", 9}
			
		};
	
	
}
	
	@Test(dataProvider = "getCircuitYearData")              //dataProvider is the keyword
	public void numberOfCircuitsYearTest(String seasonYear, int circuitNumber){
		given()
		 .pathParam("raceSeason", seasonYear)
		.when()
		 .get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
		.then()
		 .assertThat()
		  .body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitNumber));
	}
}
