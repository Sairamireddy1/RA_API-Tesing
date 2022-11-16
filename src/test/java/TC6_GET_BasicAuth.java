import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC6_GET_BasicAuth {

	@Test
	void get_user_fullJSONbody()
	{
		//set base URI...
		RestAssured.baseURI="https://postman-echo.com/basic-auth";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("postman");
		authscheme.setPassword("password");
		RestAssured.authentication = authscheme;
		
		//Req... object
		RequestSpecification http_Req = RestAssured.given();
		
		//Resp... Object
		Response response = http_Req.request(Method.GET,"");
		
		//Status code validation
		int status_code = response.statusCode();
		System.out.println("Status code is : "+status_code);
		Assert.assertEquals(status_code, 200);

		//Status line verification
		String status_line = response.statusLine();
		System.out.println("Status Line is : "+status_line);
		Assert.assertEquals(status_line, "HTTP/1.1 200 OK");
		
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("response Body is: " +responseBody);
	}
}
