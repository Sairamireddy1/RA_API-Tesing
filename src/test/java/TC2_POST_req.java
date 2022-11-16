import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_POST_req {

	@Test
	void post_user_details()
	{
		//set base URI...
		RestAssured.baseURI="https://reqres.in/api/users";
		
		//Req... object
		RequestSpecification http_Req = RestAssured.given();
		
		// Request payload sending along with POST Req...
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "sairam");
		requestParams.put("job", "tester");
		http_Req.header("Content-type","application/json");
		
		http_Req.body(requestParams.toJSONString()); //Attaching above data to this Req...
		
		//Resp... Object
		Response response = http_Req.request(Method.POST,"/3");
		
		
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("response Body is: "+responseBody);
		
		//Status code validation
		int status_code = response.statusCode();
		System.out.println("Status code is : "+status_code);
		Assert.assertEquals(status_code, 201);

		//Status line verification
		String status_line = response.statusLine();
		System.out.println("Status Line is : "+status_line);
		Assert.assertEquals(status_line, "HTTP/1.1 201 Created");
		
		//JSON Data validation
		String NAME = response.jsonPath().getString("name");
		Assert.assertEquals(NAME, "sairam");
		
	}	
}
	
