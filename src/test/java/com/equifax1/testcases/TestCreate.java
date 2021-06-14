package com.equifax1.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;



import com.equifax1.utility.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreate{
	
	@DataProvider(name = "empDataProvider")
	String[][] getEmployee() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/equifax1/utility/empData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
		String empdata[][] = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				empdata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);

			}
		}
		return (empdata);
	}

	@Test(dataProvider = "empDataProvider")
	public void addPostNewEmpoyee(String ename, String esal, String eage) {

		RestAssured.baseURI = "https://dummy.restapiexample.com/public/api/v1";

		RequestSpecification httpRequest = RestAssured.given();

		// send the request with body
		JSONObject requestPrem = new JSONObject();
		requestPrem.put("name", ename);
		requestPrem.put("salary", esal);
		requestPrem.put("age", eage);

		httpRequest.header("Contant-Type", "application/json");

		// add json to the body of the request
		httpRequest.body(requestPrem.toJSONString());

		// POST request
		Response response = httpRequest.request(Method.POST, "/create");

		// capture response body to perfom varification
		String responceBody = response.getBody().asString();
		System.out.println("Response Body is: " + responceBody);

		AssertJUnit.assertEquals(responceBody.contains(ename), true);
		AssertJUnit.assertEquals(responceBody.contains(esal), true);
		AssertJUnit.assertEquals(responceBody.contains(eage), true);

		
		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 201);

	}

	
}
