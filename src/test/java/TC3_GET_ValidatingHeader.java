import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_GET_ValidatingHeader {

	@Test
	void get_user_details_header()
	{
		//set base URI...
		RestAssured.baseURI="https://reqres.in/api/users";
		
		//Req... object
		RequestSpecification http_Req = RestAssured.given();
		
		//Resp... Object
		Response response = http_Req.request(Method.GET,"/3");
		
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("response Body is: "+responseBody);
		
		//Status code validation
		int status_code = response.statusCode();
		System.out.println("Status code is : "+status_code);
		Assert.assertEquals(status_code, 200);

		//Status line verification
		String status_line = response.statusLine();
		System.out.println("Status Line is : "+status_line);
		Assert.assertEquals(status_line, "HTTP/1.1 200 OK");
		
		//Capture detailes of HEADERS from response
		String contentType= response.header("Content-Type");
		System.out.println("HEADER contentType : "+contentType);
		Assert.assertEquals(contentType,"application/json; charset=utf-8");
		
		
	}
	
}
