package com.equifax1.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.equifax1.utility.RestUtilities;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestDELETE {

	public RequestSpecification httpRequest;
	public Response response;

	String empName = RestUtilities.empName();
	String empSalary = RestUtilities.empSal();
	String empAge = RestUtilities.empAge();

	@Test
	public void createEmployee() throws InterruptedException {
		 RequestSpecification httpRequest;
		Response response;

		String empName = RestUtilities.empName();
		String empSalary = RestUtilities.empSal();
		String empAge = RestUtilities.empAge();

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		// JsonPath object instance from the reference
		JsonPath jsonPath = response.jsonPath();

		// capture id
		String empID = jsonPath.get("[1].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID);
		Thread.sleep(6000);


		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);

	}
}
