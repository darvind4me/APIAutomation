import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffrentRequestBodyTest {
	
	FileReader fr;
	
	@Test
	public void hashmapRequest() {
		
		HashMap requestData = new HashMap();
		requestData.put("name", "AAA");
		requestData.put("job", "teacher");
		
		given()
			.contentType("application/json")
			.body(requestData)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("AAA"))
			.body("job", equalTo("teacher"))
			.log().all();
		}

	@Test
	public void jsonRequest() {
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", "bbbb");
		requestData.put("job", "DJ");
		
		given()
			.contentType("application/json")
			.body(requestData.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("bbbb"))
			.body("job", equalTo("DJ"))
			.log().all();
		}


	@Test
	public void pojoRequest() {
		
		Pojor_RequestData requestData = new Pojor_RequestData();
		requestData.setName("CCC");
		requestData.setJob("Code");
		
		
		given()
			.contentType("application/json")
			.body(requestData)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("CCC"))
			.body("job", equalTo("Code"))
			.log().all();
		}

	@Test
	public void externalJSONFileRequest() {
		
		File requestDataFile = new File(".\\requestBody.json");
	
		try {
			fr = new FileReader(requestDataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("request File not found");
		}
		JSONTokener jt =new JSONTokener(fr);
		JSONObject requestData = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(requestData.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("morpheus"))
			.body("job", equalTo("leader"))
			.log().all();
		}
	
	
}
