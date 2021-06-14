package com.equifax1.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestGET {

	@Test(enabled = true)
	void getWeatherDetails() {
		//specified the url
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		//request object
		RequestSpecification httpRequest=RestAssured.given();
		//response object
		Response response=httpRequest.request(Method.GET,"/employees");
		//print response
		String responBody=response.getBody().asString();
		System.out.println("Response Body is : "+responBody);
		//status code validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is : "+statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
		//status line
		String statusLine=response.getStatusLine();
		System.out.println("Status line :"+statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");			
		
	}
}
