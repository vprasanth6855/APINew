package com.rest.api.get;

import org.testng.Assert;
import org.testng.annotations.Test;

import XmlUtil.XmlParser;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WithXml {
	
	@Test
	public void getUserResponseXml_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
		 .contentType("application/json")
		 .header("Authorization", "Bearer c48d7cee7791ff0702943d6d716fbfd20328631653c38eb90823d2305a06d072")
		 .header("Accept", "application/xml")
		.when().log().all()
		 .get("/public-api/users?name=Aoa n");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
//		//getting xml values using xmlPath from restassured
//		XmlPath xmlPath = response.xmlPath();
//		String emailValue = xmlPath.get("data.email");
//		System.out.println("email value is: "+emailValue);
//		
//		Assert.assertEquals(emailValue, "aoa@test.com");
		
		String responseXmlString = response.prettyPrint();
		XmlParser xp = new XmlParser(responseXmlString);
		
		String value = xp.getTextContent("//response//_meta/success");
		System.out.println(value);
		Assert.assertEquals(value, "true");
		
		String id = xp.getTextContent("//result//id");
		System.out.println("user id value is: "+id);
		
	
		
		

		
		 
	}

}
