package TestCases;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import utilities.RestUtils;

public class TC004_Update_Employee extends TestBase{
	String empName = RestUtils.empname();
	String empSal = RestUtils.empsal();
	String empAge = RestUtils.empage();
	
	
	@BeforeClass
	void updateEmployee() {
		logger.info("*************Started TC004 Update Employee");

		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("sal", empSal);
		requestParams.put("age", empAge);
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		respone=httpRequest.request(Method.PUT,"/update/"+empID);
		
		
	}
	@Test
	void checkResponseBody() {
		logger.info("******To check response body*************");
		String responsebody = respone.getBody().asString();
		logger.info("Response body is:" + responsebody);
		Assert.assertTrue(responsebody != null);
		Assert.assertEquals(responsebody.contains(empName), true);
		Assert.assertEquals(responsebody.contains(empAge), true);
		Assert.assertEquals(responsebody.contains(empSal), true);
		Assert.assertEquals(responsebody.contains("Successfully! Record has been updated."), true);

	}

	@Test
	void checkStatusCode() {
		logger.info("**********Checking response status code*********");
		int statuscode = respone.getStatusCode();
		logger.info("Response status code:" + statuscode);
		Assert.assertEquals(statuscode, 200);

	}

	@Test
	void checkContentType() {
		logger.info("**********Checking content type*********");

		String contentytpe = respone.getContentType();
		logger.info("Content type is:" + contentytpe);
		Assert.assertEquals(contentytpe, "application/json");
	}

	@Test
	void checkResponseTime() {
		logger.info("**********Checking response  Time*********");
		long responsetime = respone.getTime();
		logger.info("Response time:" + responsetime);
		if (responsetime > 2000)
			logger.info("Respons time is greater than 2000");

		Assert.assertTrue(responsetime < 3000);

	}

	@AfterClass
	void tearDown() {
		logger.info("***Ended TC004 Update  employee");
	}

}
