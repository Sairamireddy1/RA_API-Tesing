import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_GET_validatingJSONbody {

	@Test
	void get_user_fullJSONbody()
	{
		//set base URI...
		RestAssured.baseURI="https://reqres.in/api/users";
		
		//Req... object
		RequestSpecification http_Req = RestAssured.given();
		
		//Resp... Object
		Response response = http_Req.request(Method.GET,"/3");
		
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("response Body is: " +responseBody);
		
		Assert.assertEquals(responseBody.contains("Emma"), true);
		
		//printing json values using keys.
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("data"));
		System.out.println(jsonpath.get("data.id"));
		System.out.println(jsonpath.get("data.email"));
		System.out.println(jsonpath.get("data.avatar"));
		
	}
	
}
