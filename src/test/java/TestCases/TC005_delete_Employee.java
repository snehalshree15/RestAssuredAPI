package TestCases;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_delete_Employee extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("********Started TC004 Delete employee*************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		respone = httpRequest.request(Method.GET, "/employees");

//		JsonPath jp = respone.jsonPath();
//		String empid = jp.get("[0].id");

		respone = httpRequest.request(Method.DELETE, "/delete/" + empID);
		Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {
		logger.info("******To check response body*************");
		String responsebody = respone.getBody().asString();
		logger.info("Response body is:" + responsebody);
		Assert.assertTrue(responsebody != null);
		
		Assert.assertEquals(responsebody.contains("Successfully! Record has been deleted"), true);

	}

//	@Test
//	void checkStatusCode() {
//		logger.info("**********Checking response status code*********");
//		int statuscode = respone.getStatusCode();
//		logger.info("Response status code:" + statuscode);
//		Assert.assertEquals(statuscode, 200);
//
//	}
//
//	@Test
//	void checkContentType() {
//		logger.info("**********Checking content type*********");
//
//		String contentytpe = respone.getContentType();
//		logger.info("Content type is:" + contentytpe);
//		Assert.assertEquals(contentytpe, "application/json");
//	}
//
//	@Test
//	void checkResponseTime() {
//		logger.info("**********Checking response  Time*********");
//		long responsetime = respone.getTime();
//		logger.info("Response time:" + responsetime);
//		if (responsetime > 2000)
//			logger.info("Respons time is greater than 2000");
//
//		Assert.assertTrue(responsetime < 3000);
//
//	}
//
//	@AfterClass
//	void tearDown() {
//		logger.info("***Ended TC005 Delete  employee");
//	}

}
