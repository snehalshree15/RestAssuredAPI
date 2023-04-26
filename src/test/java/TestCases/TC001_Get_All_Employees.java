package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	public void getAllEmployees() {

		logger.info("*******Started TC001 get all employees*****");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		respone = httpRequest.request(Method.GET, "/employees");
	}

	@Test
	void checkResponseBody() {
		logger.info("**********Checking response body*********");
		String responsebody = respone.getBody().asString();
		logger.info("Response body" + responsebody);
		Assert.assertTrue(responsebody != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("**********Checking response status code*********");

		int statuscode = respone.getStatusCode();
		logger.info("Response status code:" + statuscode);

		Assert.assertEquals(statuscode, 200);

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

	@Test
	void checkContentType() {
		logger.info("**********Checking content type*********");

		String contentytpe = respone.getContentType();
		logger.info("Content type is:" + contentytpe);
		Assert.assertEquals(contentytpe, "application/json");
	}

	@AfterClass
	void tearDown() {
		logger.info("Ended TC001 Get All Employees");
	}

}
