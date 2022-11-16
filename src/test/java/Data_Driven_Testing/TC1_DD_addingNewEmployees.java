package Data_Driven_Testing;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.XLUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_DD_addingNewEmployees {

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "stringdata")
	void post_new_employees(String Dname,String Djob,String Dsalary)
	{
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		RequestSpecification http_Req = RestAssured.given();
		
		//Here we created data which we can send along with the post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", Dname);
		requestParams.put("job", Djob);
		requestParams.put("slary", Dsalary);
		
		//Add header stating the request body is JSON
		http_Req.header("Content-Type","application/json; charset=utf-8");
		
		//Add JSON to the body of the request
		http_Req.body(requestParams.toJSONString());
		
		//POST Req...
		Response response = http_Req.request(Method.POST,"/2");
		
		//Capture the Response BODY to perform Validations
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body is: "+responseBody);
		
		Assert.assertEquals(responseBody.contains(Dname),true);
		Assert.assertEquals(responseBody.contains(Djob),true);
		Assert.assertEquals(responseBody.contains(Dsalary),true);
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 201);
			
	}

	@DataProvider(name="stringdata")
	String[][] getEMP_data() throws IOException
	{
		String path = "./TestData/sai.xlsx";
		int rowcount = XLUtils.getRowCount(path,"Sheet1");		
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String empdata[][] = new String[rowcount][colcount];
		for(int i=1; i<rowcount+1; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				empdata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
//		String empdata[][] = { {"sairam","tester","50000"},{"animi","Developer","100000"},{"naveen","DataScientist","50000"} };
		return(empdata);
	}
	
}
