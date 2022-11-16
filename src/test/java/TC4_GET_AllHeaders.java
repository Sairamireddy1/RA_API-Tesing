import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_GET_AllHeaders {

	@Test
	void get_user_all_details_headers()
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
		
		//printing all the headers
		Headers all_headers = response.headers(); //capture all headers from Resp...
		
		for(Header header:all_headers)
		{
			System.out.println(header.getName()+"===="+header.getValue());
		}	
		
	}
	
}
